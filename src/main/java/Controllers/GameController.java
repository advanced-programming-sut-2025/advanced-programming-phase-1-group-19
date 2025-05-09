package Controllers;

import Modules.*;
import Modules.Animal.*;
import Modules.Enums.*;
import Modules.Farming.*;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Map.*;
import Modules.Tools.*;
import Views.*;

import java.util.ArrayList;
import java.util.Random;

public class GameController extends Controller {
    private static GameController instance;

    private GameController() {
    }

    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    @Override
    public GameMessage showCurrentMenu() {
        return new GameMessage(null, Menu.GameMenu.toString());
    }

    private final App app = App.getInstance();

    private boolean hasActiveGame(User user) {
        return user.getCurrentGame() != null;
    }

    private boolean isMapIdValid(int mapId) {
//        TODO: check for usage
        return true;
    }

    private boolean hasEnoughEnergy(int amount) {
//        TODO: check for usage
        return true;
    }

    public GameMessage startNewGame(String[] usernames) {
        if (usernames.length > 3) {
            return new GameMessage(null, "Too many players!");
        }
        User[] users = new User[usernames.length + 1];
        users[0] = app.getCurrentUser();
        for (int i = 0; i < usernames.length; i++) {
            User user = app.getUserByUsername(usernames[i]);
            if (user == null) {
                return new GameMessage(null, "User " + usernames[i] + " not found!");
            }
            if (hasActiveGame(user)) {
                return new GameMessage(null, "User " + usernames[i] + " is already in a game!");
            }
            users[i + 1] = user;
        }
        GameMenu menu = GameMenu.getInstance();
        String mapsPreview = "Users are set successfully, here are the available maps.\n";
        for (FarmMap farmMap : FarmMap.values()) {
            mapsPreview += farmMap.name() + " map:\n";
            mapsPreview += farmMap.printMap() + "\n";
        }
        mapsPreview = mapsPreview.replace("H", "\u001B[35mH\u001B[0m");
        mapsPreview = mapsPreview.replace("G", "\u001B[32mG\u001B[0m");
        mapsPreview = mapsPreview.replace("L", "\u001B[34mL\u001B[0m");
        mapsPreview = mapsPreview.replace("Q", "\u001B[33mQ\u001B[0m");
        mapsPreview += "Press ENTER to continue...";
        menu.getSingleLine(mapsPreview);
        int[] mapIDs = new int[users.length];
        String error = "";
        for (int i = 0; i < users.length; i++) {
            String idString = menu.getSingleLine(error + "choose map for user " + users[i].getUsername());
            error = "";
            if (!idString.matches("[1-4]")) {
                error = "Wrong format, first map was selected!\n";
                mapIDs[i] = 1;
            } else {
                mapIDs[i] = Integer.parseInt(idString);
            }
        }
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Farm> farms = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            Farm farm = new Farm(FarmMap.getFarmMap(mapIDs[i]), i);
            Player player = new Player(users[i], farm);
            farms.add(farm);
            players.add(player);
        }
        Map map = new Map(farms);
        Game game = new Game(players, map);

        app.addGame(game);
        app.setCurrentGame(game);
        app.setCurrentGameStarter(app.getCurrentUser());

        for (User user : users) {
            user.setCurrentGame(game);
        }
        return new GameMessage(null, error + "New game created successfully!!!");
    }

    public GameMessage loadGame() {
//        TODO: fix this
        return null;
    }

    public GameMessage exitGame() {
        App app = App.getInstance();
        if(app.getCurrentGame() == null) {
            app.setCurrentMenu(Menu.MainMenu);
            return new GameMessage(null, "you have not started a game yet! you are now in main menu!");
        }
        if (!app.getCurrentGameStarter().equals(app.getCurrentUser())) {
            return new GameMessage(null, "you can't exit game!");
        }
        // TODO: save game
        app.setCurrentGame(null);
        app.setCurrentMenu(Menu.MainMenu);
        return new GameMessage(null, "exited game successfully! you are now in main menu!");
    }

    public GameMessage forceTerminate() {
        GameMenu gameMenu = GameMenu.getInstance();
        boolean quit = true;
        for (int i = 0; i < App.getInstance().getCurrentGame().getPlayers().size() - 1; i++) {
            String input;
            do {
                input = gameMenu.getSingleLine("");
                if (input.matches("^\\s*n\\s*$")) {
                    quit = false;
                } else if (input.matches("^\\s*y\\s*$")) {

                } else {
                    System.out.println("invalid vote");
                }
            }
            while (!input.matches("^\\s*n\\s*$") && !input.matches("^\\s*y\\s*$"));
        }
        if (quit) {
            for (Player player : App.getInstance().getCurrentGame().getPlayers()) {
                player.getUser().setCurrentGame(null);
            }
            App.getInstance().getGames().remove(App.getInstance().getCurrentGame());
            App.getInstance().setCurrentGame(null);
            App.getInstance().setCurrentGameStarter(null);
            return new GameMessage(null, "Game has been terminated!");
        } else {
            return new GameMessage(null, "Not enough votes");
        }
    }

    public GameMessage nextTurn() {
        Game game = App.getInstance().getCurrentGame();
        do {
            game.setNextPlayer();
            game.setInGameMenu(null);
        }while(game.getCurrentPlayer().isFainted());
        return new GameMessage(null, "next turn");
    }

    public GameMessage showTime() {
        int hour = App.getInstance().getCurrentGame().getTime().getHour();
        return new GameMessage(null, String.valueOf(hour));
    }

    public GameMessage showDate() {
        Time time = App.getInstance().getCurrentGame().getTime();
        int date = time.getDay();
        Season season = time.getSeason();
        return new GameMessage(null, date + " " + season.toString());
    }

    public GameMessage showDateTime() {
        Time time = App.getInstance().getCurrentGame().getTime();
        int hour = time.getHour();
        int date = time.getDay();
        Season season = time.getSeason();
        return new GameMessage(null, hour + " " + date + " " + season.toString());
    }

    public GameMessage showDayOfWeak() {
        return new GameMessage(null, App.getInstance().getCurrentGame().getTime().calculateWeekDay());
    }

    public GameMessage showSeason() {
        Time time = App.getInstance().getCurrentGame().getTime();
        Season season = time.getSeason();
        return new GameMessage(null, season.toString());
    }

    public GameMessage cheatAdvanceTime(int x) {
        for (int i = 0; i < x; i++)
            App.getInstance().getCurrentGame().nextHour();
        return new GameMessage(null, "time traveled " + x + " hours");
    }

    public GameMessage cheatAdvanceDate(int x) {
        for (int i = 0; i < 22 * x; i++)
            App.getInstance().getCurrentGame().nextHour();
        return new GameMessage(null, "time traveled " + x + " days");
    }

    public GameMessage cheatThor(int x, int y) {
        App.getInstance().getCurrentGame().thor(new Position(x, y));
        return new GameMessage(null, "you thor " + x + " " + y);
    }

    public GameMessage showWeather() {
        return new GameMessage(null, "today weather is : " + App.getInstance().getCurrentGame().getTodayWeather());
    }

    public GameMessage weatherForecast() {
        return new GameMessage(null, "tomorrow weather is forecasted : " + App.getInstance().getCurrentGame().getTomrrowWeather());
    }

    public GameMessage walk(Position end) {
        Game game = app.getCurrentGame();
        Map map = game.getMap();
        Player player = game.getCurrentPlayer();
        Position start = player.getPosition();
        if (start.equals(end)) {
            return new GameMessage(null, "Dude you are already there :/");
        }
        ArrayList<Tile> path = map.getPath(start, end);
        if (path == null) {
            return new GameMessage(null, "Ops, sorry you cant go there");
        }
        int moves = 0;
        String faintMessage = "";
        for (Tile tile : path) {
            player.setPosition(tile.getPosition());
            moves++;
            if (moves % 20 == 0) {
//                TODO: reduce energy by one;
//                TODO: go to next person and faint if energy == 0
//                TODO: set faintMessage to "Oh you have fainted middle way :(\n"
            }
        }
        return new GameMessage(null, faintMessage + "Your current position is (" + player.getPosition().x + ", " + player.getPosition().y + ")");
    }

    public GameMessage cheatForecast(String weather) {
        if (Weather.getWeatherType(weather) == null) {
            return new GameMessage(null, "not such weather type");
        }
        App.getInstance().getCurrentGame().setTomorrowWeather(Weather.getWeatherType(weather));
        return new GameMessage(null, "you successfully cheated weather forecast!");
    }

    public GameMessage showEnergy() {
        App app = App.getInstance();
        return new GameMessage(null, "Your energy is: " + app.getCurrentGame().getCurrentPlayer().getEnergy().getAmount());
    }

    public GameMessage showInventory() {
        StringBuilder stringBuilder = new StringBuilder();
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        for (java.util.Map.Entry<Item, Integer> entry : player.getBackPack().getItems().entrySet()) {
            Item item = entry.getKey();
            Integer value = entry.getValue();
            stringBuilder.append("Item: ").append(item.getName())
                    .append(", Quantity: ").append(value)
                    .append("\n");
        }
        return new GameMessage(null, "Your BackPack: \n" + stringBuilder.toString());
    }

    public GameMessage inventoryTrash(String itemName, int number, boolean delete) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        if (delete) {
            for (Item item : player.getBackPack().getItems().keySet()) {
                if (item.getName().equals(itemName)) {
                    //TODO: add refund!
                    player.getBackPack().getItems().remove(item);
                    break;
                }
            }
            return new GameMessage(null, "You fully trashed item " + itemName);
        }
        for (Item item : player.getBackPack().getItems().keySet()) {
            if (item.getName().equals(itemName)) {
                //TODO: add refund!
                int currentQty = player.getBackPack().getItems().get(item);
                if (currentQty > number) {
                    player.getBackPack().getItems().put(item, currentQty - number);
                } else {
                    player.getBackPack().getItems().remove(item);
                }
                break;
            }
        }
        return new GameMessage(null, "You successfully trashed " + number + " of " + itemName);
    }

    public GameMessage cheatEnergySet(int amount) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.addEnergy(amount - player.getEnergy().getAmount());
        return new GameMessage(null, "New energy set to " + amount);
    }

    public GameMessage unlimitedEnergySet() {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.getEnergy().setUnlimited(true);
        return new GameMessage(null, "Now your energy is unlimited");
    }

    public GameMessage buildGreenHouse() {
//        TODO: check if we have enough coin and wood!
        return null;
    }

    public GameMessage equipTool(String toolName) {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        boolean toolFound = false;
        for (java.util.Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            // only one tool of each type!
            if (item.getName().equals(toolName)) {
                App.getInstance().getCurrentGame().getCurrentPlayer().setCurrentTool((Tool) item);
                toolFound = true;
                break;
            }
        }
        if (!toolFound) {
            return new GameMessage(null, "you don't have that tool!");
        } else {
            return new GameMessage(null, "You have equipped " + toolName);
        }
    }

    public GameMessage showCurrentTool() {
        Tool tool = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentTool();
        if (tool == null) {
            return new GameMessage(null, "you don't have equipped tool!");
        } else {
            return new GameMessage(null, "You have equipped " + tool.getName());
        }
    }

    public GameMessage showAllTools() {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        StringBuilder stringBuilder = new StringBuilder("All tools: \n");
        for (java.util.Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            if (item instanceof Tool tool) {
                stringBuilder.append("Tool: ").append(tool.getName()).append("\n");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return new GameMessage(null, stringBuilder.toString());
    }

    public GameMessage upgradeTool(String toolName) {
        // TODO:check if in blacksmith!
        // TODO: check if enough money and energy
        return null;
    }

    public GameMessage useTool(String direction) {


//        TODO: fix this important!!!!
        Tool tool = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentTool();
        switch (direction) {
            case "u": {

                break;
            }
            case "d": {

                break;
            }
            case "l": {

                break;
            }
            case "r": {
                break;
            }
            case "ur": {
                break;
            }
            case "ul": {
                break;
            }
            case "dr": {
                break;
            }
            case "dl": {
                break;
            }
            default: {

            }
        }
        return null;
    }

    public GameMessage howMuchWater() {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        WateringCan wateringCan = (WateringCan) backPack.getToolByType("wateringCan");
        return new GameMessage(null, "you have " + wateringCan.getCurrentCapacity() + " water in watering can");
    }

    public GameMessage printMap(Position position, int size) {
//        if (size > 100) {
//            return new GameMessage(null, "please use sizes smaller than 100");
//        }
        Game game = App.getInstance().getCurrentGame();
        Map map = game.getMap();
        Tile tile22 = map.getTile(new Position(10, 10));
        char[][] all = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                char c = ' ';
                Tile tile = map.getTile(new Position(position.x + i, position.y + j));
                if (tile != null) {
                    Building building = tile.getBuilding();
                    if (building == null) {
                        c = '.';
                    } else if (building instanceof House) {
                        c = 'H';
                    } else if (building instanceof GreenHouse) {
                        c = 'G';
                    } else if (building instanceof Lake) {
                        c = 'L';
                    } else if (building instanceof Quarry) {
                        c = 'Q';
                    }
                }
                all[i][j] = c;
            }
        }
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                ret.append(all[i][j]);
            }
            ret.append("\n");
        }
        return new GameMessage(null, ret.toString());
    }

    public GameMessage helpPrintMap() {
        String ret = "";
        ret += ". ~> empty tiles\n";
        ret += "\u001B[35mH\u001B[0m ~> house tiles\n";
        ret += "\u001B[32mG\u001B[0m ~> green house tiles\n";
        ret += "\u001B[34mL\u001B[0m ~> lake tiles\n";
        ret += "\u001B[33mQ\u001B[0m ~> quarry tiles\n";

//        TODO: fix this if needed to show other stuff
        return new GameMessage(null, ret);
    }

    public GameMessage openHouseMenu() {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
//        TODO:check if the player is near to the house or not
        Game game = app.getCurrentGame();
        game.setInGameMenu(InGameMenu.houseMenu);
        return new GameMessage(null, "You opened house menu");
    }

    public GameMessage petAnimal(String animalName){
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        Animal animal1=player.getFarm().getBarn().getAnimalByName(animalName);
        Animal animal2=player.getFarm().getBarn().getAnimalByName(animalName);
        if(animal1==null && animal2==null){
            return new GameMessage(null,"There is no such animal");
        }
        if(animal1!=null && Math.abs(player.getPosition().x-animal1.getPosition().x)<=1 && Math.abs(player.getPosition().y-animal1.getPosition().y)<=1){
            animal1.increaseFriendship(15);
            animal1.setLastPettingTime(app.getCurrentGame().getTime());
            return new GameMessage(null,"You successfully pet "+animalName);
        }

        if(animal2!=null && Math.abs(player.getPosition().x-animal2.getPosition().x)<=1 && Math.abs(player.getPosition().y-animal2.getPosition().y)<=1){
            animal2.increaseFriendship(15);
            animal2.setLastPettingTime(app.getCurrentGame().getTime());
            return new GameMessage(null,"You successfully pet "+animalName);
        }

        return new GameMessage(null,"You do not have access to this animal");
    }

    public GameMessage cheatFriendship(String animalName,int amount){
        App app = App.getInstance();
        Game game=app.getCurrentGame();
        Player player=app.getCurrentGame().getCurrentPlayer();
        Animal animal=player.getFarm().getBarn().getAnimalByName(animalName);
        if(animal==null){
            return new GameMessage(null,"There is no such animal");
        }
        animal.setFriendship(amount);
        return new GameMessage(null,"You successfully set your friendship : "+amount);
    }

    public GameMessage getFriendship(){
        App app = App.getInstance();
        Game game=app.getCurrentGame();
        Player player=app.getCurrentGame().getCurrentPlayer();
        boolean hasBeenPetToday=false;
        boolean hasBeenFedToday=false;
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append("You have :\n");
        if(player.getFarm().getBarn()!=null){
            stringBuilder.append("Barn Animals:\n");
            for (Animal animal1 : player.getFarm().getBarn().getAnimals()) {
                stringBuilder.append(animal1.getName()+"\n");
                hasBeenPetToday=game.getTime().getDay()==animal1.getLastPetingTime().getDay() &&
                        game.getTime().getSeason()==animal1.getLastPetingTime().getSeason();
                if(hasBeenPetToday){
                    stringBuilder.append("Has Your Animal Been Pet Today : true\n\n");
                }
                else{
                    stringBuilder.append("Has Your Animal Been Pet Today : false\n\n");
                }
            }
        }
        if(player.getFarm().getCoop()!=null){
            stringBuilder.append("Coop Animals:\n");
            for (Animal animal1 : player.getFarm().getBarn().getAnimals()) {
                stringBuilder.append(animal1.getName()+"\n");
                hasBeenFedToday=game.getTime().getDay()==animal1.getLastFeedingTime().getDay() &&
                        game.getTime().getSeason()==animal1.getLastFeedingTime().getSeason();

                if(hasBeenFedToday){
                    stringBuilder.append("Has Your Animal Been Fed Today : true\n\n");
                }
                else{
                    stringBuilder.append("Has Your Animal Been Fed Today : false\n\n");
                }
            }
        }

        return new GameMessage(null,stringBuilder.toString());
    }

    public GameMessage shepherdAnimals(String animalName,int x,int y) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if (!animal.isOutside()) {
            if (game.getTodayWeather() != Weather.sunny) {
                return new GameMessage(null, "The animal can not go outside in this weather");
            }
            animal.setOutside(true);
            animal.setPosition(new Position(x, y));
            return new GameMessage(null,"The animal successfully moved");
        } else {
            if (animal.getType().isInCage()) {
                animal.setPosition(player.getFarm().getCoop().getPlacedTile().getPosition());
                animal.setOutside(false);
                return new GameMessage(null, "The animal entered the cage");
            } else {
                animal.setPosition(player.getFarm().getBarn().getPlacedTile().getPosition());
                animal.setOutside(true);
                animal.setLastFeedingTime(game.getTime());
                animal.increaseFriendship(8);
                return new GameMessage(null, "The animal entered the barn");
            }

        }
    }

    public GameMessage feedingHay(String animalName) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if (animal.isOutside()) {
            return new GameMessage(null, "The animal must be in the coop to feed hay");
        }
        animal.setLastFeedingTime(game.getTime());
        return new GameMessage(null,"The animal successfully fed");
    }

    public GameMessage showProducts(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        if(player.getFarm().getBarn() != null){
            for (Animal animal : player.getFarm().getBarn().getAnimals()) {
                if(animal.doesProduce()){
                    stringBuilder.append(animal.getName()+" Produces:  ");
                    stringBuilder.append(animal.whichProduct().getName());
                    stringBuilder.append("\n");
                }
            }
        }
        if(player.getFarm().getCoop() != null){
            for (Animal animal : player.getFarm().getCoop().getAnimals()) {
                if(animal.doesProduce()){
                    stringBuilder.append(animal.getName()+" Produces:  ");
                    stringBuilder.append(animal.whichProduct().getName());
                    stringBuilder.append("\n");
                }
            }
        }
        return new GameMessage(null,stringBuilder.toString());
    }

    public GameMessage collectProducts(String animalName){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal= player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal == null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }
        if(animal.getCurrentProduct() != null){
            if(animal.getName().equals("cow") || animal.getName().equals("goat")){
//               TODO:check if there is watering can in inventory
                player.decreaseEnergy(4);
                if(player.getBackPack().getCapacity()==0){
                    return new GameMessage(null,"There is no space in your backpack");
                }

                player.getBackPack().addItem(animal.getCurrentProduct(),1);
                animal.setCurrentProduct(null);
                return new GameMessage(null,animal.getCurrentProduct().getName()+" collected");
            }
            else if(animal.getName().equals("sheep")){
//               TODO:check if there is gheuchi in inventory
                player.decreaseEnergy(4);
                if(player.getBackPack().getCapacity()==0){
                    return new GameMessage(null,"There is no space in your backpack");
                }
                player.getBackPack().addItem(animal.getCurrentProduct(),1);
                animal.setCurrentProduct(null);
                return new GameMessage(null,animal.getCurrentProduct().getName()+" collected");
            }
            else if(animal.getName().equals("pig")){
                if(animal.isOutside()){
                    if(player.getBackPack().getCapacity()==0){
                        return new GameMessage(null,"There is no space in your backpack");
                    }
                    player.getBackPack().addItem(animal.getCurrentProduct(),1);
                    animal.setCurrentProduct(null);
                    return new GameMessage(null,animal.getCurrentProduct().getName()+" collected");
                }
            }
        }
        return null;
    }

    public GameMessage sellAnimal(String animalName) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal != null) {
            int price = animal.calSellingPrice();
//            TODO:increase player money
            player.getFarm().getBarn().getAnimals().remove(animal);
            return new GameMessage(null,animal.getCurrentProduct().getName()+" sold");
        }
        else{
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
            if (animal != null) {
                int price = animal.calSellingPrice();
//                TODO:increase player money
                player.getFarm().getCoop().getAnimals().remove(animal);
                return new GameMessage(null,animal.getCurrentProduct().getName()+" sold");
            }
            return new GameMessage(null, "There is no such animal");
        }
    }



    private FishType generateRandomFish(Season season,boolean legendary) {
        Random rand = new Random();
        if(!legendary){
            int fishCount = rand.nextInt(4) + 1;
            if(season==Season.spring){
                switch (fishCount) {
                    case 1:{
                        return FishType.flounder;
                    }
                    case 2:{
                        return FishType.lionFish;
                    }
                    case 3:{
                        return FishType.herring;
                    }
                    case 4:{
                        return FishType.ghostFish;
                    }
                }
            }
            else if(season==Season.winter){
                switch (fishCount) {
                    case 1:{
                        return FishType.midnightCarp;
                    }
                    case 2:{
                        return FishType.squid;
                    }
                    case 3:{
                        return FishType.tuna;
                    }
                    case 4:{
                        return FishType.perch;
                    }
                }
            }
            else if(season==Season.fall){
                switch (fishCount) {
                    case 1:{
                        return FishType.salmon;
                    }
                    case 2:{
                        return FishType.sardine;
                    }
                    case 3:{
                        return FishType.shad;
                    }
                    case 4:{
                        return FishType.blueDiscus;
                    }
                }
            }
            else if(season==Season.summer){
                switch (fishCount) {
                    case 1:{
                        return FishType.tilapia;
                    }
                    case 2:{
                        return FishType.dorado;
                    }
                    case 3:{
                        return FishType.sunFish;
                    }
                    case 4:{
                        return FishType.rainbowTrout;
                    }
                }
            }
        }
        else{
            int fishCount = rand.nextInt(5) + 1;
            if(season==Season.spring){
                switch (fishCount) {
                    case 1:{
                        return FishType.flounder;
                    }
                    case 2:{
                        return FishType.lionFish;
                    }
                    case 3:{
                        return FishType.herring;
                    }
                    case 4:{
                        return FishType.ghostFish;
                    }
                    case 5:{
                        return FishType.legend;
                    }
                }
            }
            else if(season==Season.winter){
                switch (fishCount) {
                    case 1:{
                        return FishType.midnightCarp;
                    }
                    case 2:{
                        return FishType.squid;
                    }
                    case 3:{
                        return FishType.tuna;
                    }
                    case 4:{
                        return FishType.perch;
                    }
                    case 5:{
                        return FishType.glacierFish;
                    }
                }
            }
            else if(season==Season.fall){
                switch (fishCount) {
                    case 1:{
                        return FishType.salmon;
                    }
                    case 2:{
                        return FishType.sardine;
                    }
                    case 3:{
                        return FishType.shad;
                    }
                    case 4:{
                        return FishType.blueDiscus;
                    }
                    case 5:{
                        return FishType.angler;
                    }
                }
            }
            else if(season==Season.summer){
                switch (fishCount) {
                    case 1:{
                        return FishType.tilapia;
                    }
                    case 2:{
                        return FishType.dorado;
                    }
                    case 3:{
                        return FishType.sunFish;
                    }
                    case 4:{
                        return FishType.rainbowTrout;
                    }
                    case 5:{
                        return FishType.crimsonFish;
                    }
                }
            }
        }
        return null;
    }

    public GameMessage fishing(String fishingPole){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.decreaseEnergy(8);
        switch (fishingPole) {
            case "Training":{
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if(item.getName().equals("Fishing Pole")){
                        Tool tool = (Tool) item;
                        if(tool.getLevel() == 0){
                            player.decreaseEnergy(8);
                            Fish fish=new Fish("fish",1,generateRandomFish(game.getTime().getSeason(),false),new Quality(Fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),tool.getLevelInfo())));
                            return new GameMessage(null,"You successfully got "+fish.getFishingCount(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel())+" of "+fish.getName());
                        }
                    }
                }
                break;
            }
            case "Bamboo":{
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if(item.getName().equals("Fishing Pole")){
                        Tool tool = (Tool) item;
                        if(tool.getLevel() == 1){
                            player.decreaseEnergy(8);
                            Fish fish=new Fish("fish",1,generateRandomFish(game.getTime().getSeason(),false),new Quality(Fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),tool.getLevelInfo())));
                            return new GameMessage(null,"You successfully got "+fish.getFishingCount(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel())+" of "+fish.getName());
                        }
                    }
                }
                break;
            }
            case "Fiberglass":{
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if(item.getName().equals("Fishing Pole")){
                        Tool tool = (Tool) item;
                        if(tool.getLevel() == 2){
                            player.decreaseEnergy(6);
                            Fish fish=new Fish("fish",1,generateRandomFish(game.getTime().getSeason(),false),new Quality(Fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),tool.getLevelInfo())));
                            return new GameMessage(null,"You successfully got "+fish.getFishingCount(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel())+" of "+fish.getName());
                        }
                    }
                }
                break;
            }
            case "Iridium":{
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if(item.getName().equals("Fishing Pole")){
                        Tool tool = (Tool) item;
                        if(tool.getLevel() == 3){
                            player.decreaseEnergy(4);
                            Fish fish=new Fish("fish",1,generateRandomFish(game.getTime().getSeason(),true),new Quality(Fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),tool.getLevelInfo())));
                            return new GameMessage(null,"You successfully got "+fish.getFishingCount(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel())+" of "+fish.getName());
                        }
                    }
                }
                break;
            }
        }
        return null;
    }

    public GameMessage craftInfo(String name) {
        PlantType plant = PlantType.getPlantTypeByName(name);
        if (plant == null) {
            return new GameMessage(null, "no plant found with this name!");
        }
        SeedType seed = plant.getSeed();
        CropType crop = plant.getCrop();

        String ret = "";
        ret += "Name: " + plant.getName() + "\n";
        ret += "Source: " + seed.getName() + " Seeds\n";
        ret += "Stages: ";
        for (int stage : plant.getStages()) {
            ret += stage + " ";
        }
        ret += "\n";
        ret += "Total Harvest Time: " + plant.getTotalTime() + "\n";
        int x = plant.getReGrowth();
        ret += "One Time: " + (x == -1);
        ret += "Regrowth Time: " + (x == -1 ? "" : x) + "\n";
        ret += "Base Sell Price: " + crop.getInitialPrice() + "\n";
        ret += "Is Edible: " + crop.isEdible() + "\n";
        ret += "Base Energy: " + crop.getEnergy() + "\n";
        ret += "Seasons: ";
        for (Season season : plant.getSeasonsAvailable()) {
            ret += season.name() + " ";
        }
        ret += "\n";
        ret += "Can Become Giant: " + plant.isCanBeComeGiant() + "\n";

        return new GameMessage(null, ret);
    }

    public GameMessage plant(SeedType seedType, Direction direction) {
        if (direction == null) {
            return new GameMessage(null, "select a valid direction!\n(u, d, l, r, ur, ul, dr, dl)");
        }
        if (seedType == null) {
            return new GameMessage(null, "select a valid seed!");
        }
        Game game = app.getCurrentGame();
        Map map = game.getMap();
        Player player = game.getCurrentPlayer();
        BackPack inventory = player.getBackPack();
        Seed seed = inventory.getSeedInBachPack(seedType);
        if (seed == null) {
            return new GameMessage(null, "you dont have that seed!");
        }
        PlantType plantType = PlantType.getPlantTypeBySeed(seedType);
        if (plantType == null) {
            return new GameMessage(null, "oops, check the plantType and seedType files :(");
        }
        if(!plantType.getSeasonsAvailable().contains(game.getTime().getSeason())) {
            return new GameMessage(null, "you can't plant that in this season!");
        }

        Position playerPosition = player.getPosition();
        Position targetPosition = new Position(playerPosition.x, playerPosition.y);
        targetPosition.move(direction);

        Plant plant = new Plant(plantType, game.getTime());
        Tile tile = map.getTile(targetPosition);
//        TODO: check for greenHouse
        if (tile == null || tile.getObject() != null || tile.getBuilding() != null) {
            return new GameMessage(null, "you must choose a valid and empty tile!");
        }

        tile.setObject(plant);
        tile.setContainsPlant(true);
        plant.setPlacedTile(tile);
        inventory.removeItem(seed, 1);

//        CHECKING FOR GIANT PLANT:
        Tile[] t = new Tile[8];
        t[0] = map.getTile(new Position(targetPosition.x - 1, targetPosition.y - 1));
        t[1] = map.getTile(new Position(targetPosition.x - 1, targetPosition.y));
        t[2] = map.getTile(new Position(targetPosition.x - 1, targetPosition.y + 1));
        t[3] = map.getTile(new Position(targetPosition.x, targetPosition.y + 1));
        t[4] = map.getTile(new Position(targetPosition.x + 1, targetPosition.y + 1));
        t[5] = map.getTile(new Position(targetPosition.x + 1, targetPosition.y));
        t[6] = map.getTile(new Position(targetPosition.x + 1, targetPosition.y - 1));
        t[7] = map.getTile(new Position(targetPosition.x, targetPosition.y - 1));
        boolean[] flag = new boolean[8];
        Plant[] p = new Plant[8];
        for (int i = 0; i < 8; i++) {
            if (t[i] == null) {
                flag[i] = false;
                continue;
            }
            if (t[i].containsPlant()) {
                p[i] = (Plant) t[i].getObject();
                if (p[i].getType().getSeed().equals(seedType) && p[i].getGianPosition() == -1) {
                    flag[i] = true;
                }
            }
            flag[i] = false;
        }
        if (flag[1] && flag[2] && flag[3]) {
            plant.setGianPosition(2);
            p[1].setGianPosition(0);
            p[2].setGianPosition(1);
            p[3].setGianPosition(3);

            Time lastWatering = Time.maximum(Time.maximum(tile.getLastWateringTime(), t[1].getLastWateringTime())
                    , Time.maximum(t[2].getLastWateringTime(), t[3].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[1].getPlantingTime())
                    , Time.minimum(p[2].getPlantingTime(), p[3].getPlantingTime()));

            tile.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            t[1].setLastWateringTime(lastWatering);
            p[1].setPlantingTime(planting);
            t[2].setLastWateringTime(lastWatering);
            p[2].setPlantingTime(planting);
            t[3].setLastWateringTime(lastWatering);
            p[3].setPlantingTime(planting);

        } else if (flag[3] && flag[4] && flag[5]) {
            plant.setGianPosition(0);
            p[3].setGianPosition(1);
            p[4].setGianPosition(3);
            p[5].setGianPosition(2);

            Time lastWatering = Time.maximum(Time.maximum(tile.getLastWateringTime(), t[3].getLastWateringTime())
                    , Time.maximum(t[4].getLastWateringTime(), t[5].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[3].getPlantingTime())
                    , Time.minimum(p[4].getPlantingTime(), p[5].getPlantingTime()));

            tile.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            t[3].setLastWateringTime(lastWatering);
            p[3].setPlantingTime(planting);
            t[4].setLastWateringTime(lastWatering);
            p[4].setPlantingTime(planting);
            t[5].setLastWateringTime(lastWatering);
            p[5].setPlantingTime(planting);

        } else if (flag[5] && flag[6] && flag[7]) {
            plant.setGianPosition(1);
            p[5].setGianPosition(3);
            p[6].setGianPosition(2);
            p[7].setGianPosition(0);


            Time lastWatering = Time.maximum(Time.maximum(tile.getLastWateringTime(), t[5].getLastWateringTime())
                    , Time.maximum(t[6].getLastWateringTime(), t[7].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[5].getPlantingTime())
                    , Time.minimum(p[6].getPlantingTime(), p[7].getPlantingTime()));

            tile.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            t[5].setLastWateringTime(lastWatering);
            p[5].setPlantingTime(planting);
            t[6].setLastWateringTime(lastWatering);
            p[6].setPlantingTime(planting);
            t[7].setLastWateringTime(lastWatering);
            p[7].setPlantingTime(planting);

        } else if (flag[7] && flag[0] && flag[1]) {
            plant.setGianPosition(3);
            p[7].setGianPosition(2);
            p[0].setGianPosition(0);
            p[1].setGianPosition(1);


            Time lastWatering = Time.maximum(Time.maximum(tile.getLastWateringTime(), t[7].getLastWateringTime())
                    , Time.maximum(t[0].getLastWateringTime(), t[1].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[7].getPlantingTime())
                    , Time.minimum(p[0].getPlantingTime(), p[1].getPlantingTime()));

            tile.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            t[7].setLastWateringTime(lastWatering);
            p[7].setPlantingTime(planting);
            t[0].setLastWateringTime(lastWatering);
            p[0].setPlantingTime(planting);
            t[1].setLastWateringTime(lastWatering);
            p[1].setPlantingTime(planting);
        }
        return new GameMessage(null, "seed planted successfully!");
    }

    public GameMessage showPlant(Position position) {
        Game game = app.getCurrentGame();
        Map map = game.getMap();
        Tile tile = map.getTile(position);
        if(tile == null) {
            return new GameMessage(null, "choose a valid tile!");
        }
        if(!tile.containsPlant() || tile.getObject() == null || !(tile.getObject() instanceof Plant)) {
            return new GameMessage(null, "this tile doesn't contain a plant!");
        }
        Plant plant = (Plant) tile.getObject();
        PlantType plantType = plant.getType();
        String ret = "";
        ret += "Name: " + plantType.getName() + "\n";
        ret += "Is Giant: " + (plant.getGianPosition() != -1) + "\n";
        ret += "Last Watering Time: " + tile.getLastWateringTime() + "\n";
        ret += "Planting Time: " + plant.getPlantingTime();
        return new GameMessage(null, ret);
    }


    @Override
    public Message exit() {
//        TODO: save game and go back to main Menu
        return null;
    }
}