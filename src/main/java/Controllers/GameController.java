package Controllers;

import Modules.*;
import Modules.Animal.*;
import Modules.Communication.FriendShip;
import Modules.Communication.Gift;
import Modules.Crafting.Material;
import Modules.Crafting.MaterialType;
import Modules.Enums.*;
import Modules.Enums.Menu;
import Modules.Enums.Season;
import Modules.Enums.Weather;
import Modules.Farming.*;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Map.*;
import Modules.Store.Store;
import Modules.Tools.*;
import Views.*;

import java.util.ArrayList;
import java.util.List;
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
        ArrayList<Store> stores = new ArrayList<>();
        stores.add(new Store("Clint"));
        stores.add(new Store("Morris"));
        stores.add(new Store("Pierre"));
        stores.add(new Store("Robin"));
        stores.add(new Store("Willy"));
        stores.add(new Store("Marnie"));
        stores.add(new Store("Gus"));

        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Farm> farms = new ArrayList<>();
        for (int i = 0; i < users.length; i++) {
            Farm farm = new Farm(FarmMap.getFarmMap(mapIDs[i]), i, stores);
            Player player = new Player(users[i], farm);
            farms.add(farm);
            players.add(player);
        }
        for(int i=0 ; i<players.size() ; i++) {
            for (Player player : players) {
                if (!players.get(i).equals(player)) {
                    players.get(i).addFriendShip(new FriendShip(player));
                }
            }
        }
        Map map = new Map(farms);
        Game game = new Game(players, map, stores);

        app.addGame(game);
        app.setCurrentGame(game);
        app.setCurrentGameStarter(app.getCurrentUser());

        for (User user : users) {
            user.setCurrentGame(game);
        }
        return new GameMessage(null, error + "New game created successfully!!!");
    }

    public GameMessage loadGame() {
        User user = app.getCurrentUser();
        if(user.getCurrentGame() == null) {
            return new GameMessage(null, "you have no game to load!");
        }
        app.setCurrentGame(user.getCurrentGame());
        app.setCurrentGameStarter(user);
        return new GameMessage(null, "Game loaded successfully!");
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
            App.getInstance().setCurrentMenu(Menu.MainMenu);
            return new GameMessage(null, "Game has been terminated! you are now in main menu!");
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
        for (int i = 0; i < 14*x;  i++)
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
        if(map.getTile(end) == null){
            return new GameMessage(null, "No transpassing");
        }
        if(map.getTile(end).getBuilding() instanceof Lake || map.getTile(end).getBuilding() instanceof Quarry){
            return new GameMessage(null, "What are you thinking? You can't go there!");
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
                player.decreaseEnergy(1);
                if(player.getEnergy().getAmount() == 0){
                    player.setFainted(true);
                    break;
                }
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
            stringBuilder.append("Item: ").append(item.toString())
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
                if (item.toString().equals(itemName)) {
                    player.addMoney((int) (item.getPrice() * player.getBackPack().getItems().get(item) * player.getTrashCan().calcRatio()));
                    player.getBackPack().getItems().remove(item);
                    break;
                }
            }
            return new GameMessage(null, "You fully trashed item " + itemName);
        }
        for (Item item : player.getBackPack().getItems().keySet()) {
            if (item.toString().equals(itemName)) {
                int currentQty = player.getBackPack().getItems().get(item);
                if (currentQty >= number) {
                    player.addMoney((int) (item.getPrice() * player.getBackPack().getItems().get(item) * player.getTrashCan().calcRatio()));
                    if(currentQty > number) {
                        player.getBackPack().getItems().put(item, currentQty - number);
                    }
                    else{
                        player.getBackPack().getItems().remove(item);
                    }
                    return new GameMessage(null, "You successfully trashed " + number + " of " + itemName);
                }
                else {
                    return new GameMessage(null, "You don't have enough of this item to trash it");
                }
            }
        }
        return new GameMessage(null, "You don't have that item!");
    }

    public GameMessage cheatEnergySet(int amount) {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        if(player.getEnergy().getMaxAmount() < amount){
            player.getEnergy().increaseMaxAmount(amount - player.getEnergy().getMaxAmount());
        }
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

        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        Player player = app.getCurrentGame().getCurrentPlayer();
        for (java.util.Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            if (item instanceof Tool && item.toString().equals(toolName)) {
                if(((Tool) item).getLevel() + 1 == ((Tool) item).getToolType().getLevels().size()){
                    return new GameMessage(null, "Your tool is at maximum level!");
                }
                if(player.getMoney() >= 2000 && ((Tool) item).getLevel() == 0){
                    player.decreaseEnergy(2000);
                    ((Tool) item).upgradeLevel();
                    return new GameMessage(null, "Your tool got upgraded " + toolName);
                }
                else if(player.getMoney() >= 5000 && ((Tool) item).getLevel() == 1){
                    player.decreaseEnergy(5000);
                    ((Tool) item).upgradeLevel();
                    return new GameMessage(null, "Your tool got upgraded " + toolName);
                }
                else if(player.getMoney() >= 10000 && ((Tool) item).getLevel() == 2){
                    player.decreaseEnergy(10000);
                    ((Tool) item).upgradeLevel();
                    return new GameMessage(null, "Your tool got upgraded " + toolName);
                }
                else if(player.getMoney() >= 25000 && ((Tool) item).getLevel() == 3){
                    player.decreaseEnergy(25000);
                    ((Tool) item).upgradeLevel();
                    return new GameMessage(null, "Your tool got upgraded " + toolName);
                }
                else{
                    return new GameMessage(null, "You don't have enough money!");
                }
            }
        }
        return new GameMessage(null, "You don't have that tool!");
    }

    public GameMessage upgradeBackPack() {
        Player player = app.getCurrentGame().getCurrentPlayer();
        BackPack backPack = player.getBackPack();
        if(backPack.getLevel() == 0){
            if(player.getMoney() < 2000){
                return new GameMessage(null, "You don't have enough money!");
            }
            player.decreaseMoney(2000);
            backPack.increaseLevel();
            return new GameMessage(null, "You got upgraded Large Back pack");
        }
        else if(backPack.getLevel() == 1){
            if(player.getMoney() < 10000){
                return new GameMessage(null, "You don't have enough money!");
            }
            player.decreaseMoney(10000);
            backPack.increaseLevel();
            return new GameMessage(null, "You got upgraded Deluxe Back pack");
        }
        return new GameMessage(null, "You couldn't upgrade Back pack");
    }

    public GameMessage upgradeTrashCan() {
        Player player = app.getCurrentGame().getCurrentPlayer();
        TrashCan trashCan = player.getTrashCan();
        if(trashCan.getLevel() == 0){
            if(player.getMoney() < 1000){
                return new GameMessage(null, "You don't have enough money!");
            }
            player.decreaseMoney(1000);
            trashCan.upgradeLevel();
            return new GameMessage(null, "You got upgraded Copper TrashCan");
        }
        else if(trashCan.getLevel() == 1){
            if(player.getMoney() < 2500){
                return new GameMessage(null, "You don't have enough money!");
            }
            player.decreaseMoney(2500);
            trashCan.upgradeLevel();
            return new GameMessage(null, "You got upgraded Steel TrashCan");
        }
        else if(trashCan.getLevel() == 2){
            if(player.getMoney() < 5000){
                return new GameMessage(null, "You don't have enough money!");
            }
            player.decreaseMoney(5000);
            trashCan.upgradeLevel();
            return new GameMessage(null, "You got upgraded Gold TrashCan");
        }
        else if(trashCan.getLevel() == 3){
            if(player.getMoney() < 12500){
                return new GameMessage(null, "You don't have enough money!");
            }
            player.decreaseMoney(12500);
            trashCan.upgradeLevel();
            return new GameMessage(null, "You got upgraded Iridium TrashCan");
        }
        return new GameMessage(null, "You couldn't upgrade TrashCan!");
    }

    public GameMessage useTool(Direction direction) {
        Tool tool = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentTool();
        if(tool == null){
            return new GameMessage(null, "You don't have a current tool!");
        }
        Player player = app.getCurrentGame().getCurrentPlayer();
        Position playerPosition = player.getPosition();
        Position targetPosition = new Position(playerPosition.x, playerPosition.y);
        targetPosition.move(direction);
        return tool.use(targetPosition);
    }

    public GameMessage howMuchWater() {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        WateringCan wateringCan = (WateringCan) backPack.getToolByType(ToolType.wateringCan);
        return new GameMessage(null, "you have " + wateringCan.getCurrentCapacity() + " water in watering can");
    }

    public GameMessage printMap(Position position, int size) {
        if (size > 250) {
            return new GameMessage(null, "please use sizes smaller than 250");
        }
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
                    TileObject tileObject = tile.getObject();
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
                    else if(building instanceof Store) {
                        c = 'S';
                    }
                    if(tileObject != null) {
                        if(tileObject instanceof Item){
                            c = 'I';
                        }
                        if(tileObject instanceof Plant){
                            c = 'P';
                        }
                        if(tileObject instanceof ShippingBin) {
                            c = 's';
                        }
                        if(tileObject instanceof Barn){
                            c = 'B';
                        }
                        if(tileObject instanceof Coop){
                            c = 'C';
                        }
                    }
                }
                all[i][j] = c;
            }
        }
        for(int i = 0; i < game.getPlayers().size(); i++){
            Player player = game.getPlayers().get(i);
            Position p = player.getPosition();
            if(p.x >= position.x && p.x < position.x + size && p.y >= position.y && p.y < position.y + size){
                all[p.x - position.x][p.y - position.y] = Character.forDigit(i+1, 10);
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
        ret += "I ~> items\n";
        ret += "P ~> plants\n";
        ret += "B ~> barn\n";
        ret += "C ~> coop\n";
        ret += "S ~> store\n";
        ret += "s ~> shipping bin\n";

//        TODO: fix this if needed to show other stuff
        return new GameMessage(null, ret);
    }

    public GameMessage openHouseMenu() {
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
//        TODO:check if the player is near the house
        Farm farm = player.getFarm();
        boolean isNearHouse = false;
        int x = player.getPosition().x;
        int y = player.getPosition().y;
//        for(int i=x-1;i<=x+1;i++){
//            for(int j=y-1;j<=y+1;j++){
//                if((i!=0 || j!=0) && farm.getTile(new Position(i,j)).getBuilding() instanceof House){
//                    isNearHouse = true;
//                }
//            }
//        }
//        if(!isNearHouse){
//            return new GameMessage(null, "You are not close to your house!");
//        }
        Game game = app.getCurrentGame();
        game.setInGameMenu(InGameMenu.houseMenu);
        return new GameMessage(null, "You opened house menu");
    }

    public GameMessage closeHouseMenu() {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        if(game.getInGameMenu() != InGameMenu.houseMenu){
            return new GameMessage(null, "You haven't open house menu!");
        }
        game.setInGameMenu(null);
        return new GameMessage(null, "You closed house menu");
    }

    public GameMessage openCraftingMenu() {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        int x = player.getPosition().x;
        int y = player.getPosition().y;
        boolean isNearHouse = false;
//        for(int i=x-1;i<=x+1;i++){
//            for(int j=y-1;j<=y+1;j++){
//                if((i!=0 || j!=0) && farm.getTile(new Position(i,j)).getBuilding() instanceof House){
//                    isNearHouse = true;
//                }
//            }
//        }
//        if(!isNearHouse){
//            return new GameMessage(null, "You are not close to your house!");
//        }
        game.setInGameMenu(InGameMenu.craftingMenu);
        return new GameMessage(null, "You opened Crafting menu");
    }

    public GameMessage closeCraftingMenu() {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Farm farm = player.getFarm();
        if(game.getInGameMenu() != InGameMenu.craftingMenu){
            return new GameMessage(null, "You haven't open crafting menu!");
        }
        game.setInGameMenu(null);
        return new GameMessage(null, "You closed Crafting menu");
    }

    public GameMessage placeItem(String itemName,String direction){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Item item = player.getBackPack().getItemByName(itemName);
        if(item == null){
            return new GameMessage(null,"There is no item with this name");
        }
        Tile tile = null;
        Direction direction1 = Direction.getDirection(direction);
        if(direction1 == null){
            return new GameMessage(null,"Wrong direction");
        }
        Position playerPosition = player.getPosition();
        Position targetPosition = new Position(playerPosition.x, playerPosition.y);
        targetPosition.move(direction1);
        tile = player.getFarm().getTile(targetPosition);
        if(tile.getObject() != null){
            return new GameMessage(null,"This position is already taken!");
        }
        item.drop(tile);
        player.getBackPack().removeItem(item,1);
        return new GameMessage(null,"You successfully placed "+itemName+"!");
    }

    public GameMessage cheatAddItem(String itemName,int amount){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Item item = Item.getItemByName(itemName);
        if(item == null){
            return new GameMessage(null,"There is no item with this name");
        }
        if(player.getBackPack().getCapacity() >= player.getBackPack().getMaxCapacity() ){
            return new GameMessage(null,"You have no remain place in your backpack");
        }
        player.getBackPack().addItem(item,amount);
        return new GameMessage(null,"You successfully add "+amount+" "+item.getName());
    }

    public GameMessage petAnimal(String animalName){
        App app = App.getInstance();
        Player player=app.getCurrentGame().getCurrentPlayer();
        if (player.getFarm().getBarn() == null && player.getFarm().getCoop() == null) {
            return new GameMessage(null, "You do not have Barn and Coop");
        }

        Animal animal = null;

        if (player.getFarm().getCoop() != null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }

        if (animal == null && player.getFarm().getBarn() != null) {
            animal = player.getFarm().getBarn().getAnimalByName(animalName);
        }

        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }

        if( Math.abs(player.getPosition().x-animal.getPosition().x)<=1 && Math.abs(player.getPosition().y-animal.getPosition().y)<=1){
            animal.increaseFriendship(15);
            animal.setLastPettingTime(app.getCurrentGame().getTime());
            return new GameMessage(null,"You successfully pet "+animalName);
        }

        return new GameMessage(null,"You do not have access to this animal");
    }

    public GameMessage cheatFriendship(String animalName,int amount){
        App app = App.getInstance();
        Game game=app.getCurrentGame();
        Player player=app.getCurrentGame().getCurrentPlayer();
        Animal animal = null;
        if(player.getFarm().getBarn() != null){
            animal=player.getFarm().getBarn().getAnimalByName(animalName);
        }
        if(animal==null && player.getFarm().getCoop() != null){
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
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
                stringBuilder.append(animal1.getFriendship()+"\n");
                if(animal1.getLastFeedingTime()==null){
                    hasBeenFedToday = false;
                }
                else if(game.getTime().getDay()==animal1.getLastFeedingTime().getDay() &&
                        game.getTime().getSeason()==animal1.getLastFeedingTime().getSeason()){
                    hasBeenFedToday=true;
                }
                if(hasBeenFedToday){
                    stringBuilder.append("Has Your Animal Been Fed Today : true\n\n");
                }
                else{
                    stringBuilder.append("Has Your Animal Been Fed Today : false\n\n");
                }
                if(animal1.getLastPetingTime()==null){
                    hasBeenPetToday = false;
                }
                else if(game.getTime().getDay()==animal1.getLastPetingTime().getDay() &&
                        game.getTime().getSeason()==animal1.getLastPetingTime().getSeason()){
                    hasBeenPetToday=true;
                }
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
            for (Animal animal1 : player.getFarm().getCoop().getAnimals()) {
                stringBuilder.append(animal1.getName()+"\n");
                stringBuilder.append(animal1.getFriendship()+"\n");
                if(animal1.getLastFeedingTime() == null){
                    hasBeenFedToday = false;
                }
                else{
                    hasBeenFedToday=game.getTime().getDay()==animal1.getLastFeedingTime().getDay() &&
                            game.getTime().getSeason()==animal1.getLastFeedingTime().getSeason();
                }
                if(hasBeenFedToday){
                    stringBuilder.append("Has Your Animal Been Fed Today : true\n\n");
                }
                else{
                    stringBuilder.append("Has Your Animal Been Fed Today : false\n\n");
                }
                if(animal1.getLastPetingTime() == null){
                    hasBeenPetToday = false;
                }
                else if(game.getTime().getDay()==animal1.getLastPetingTime().getDay() &&
                        game.getTime().getSeason()==animal1.getLastPetingTime().getSeason()){
                    hasBeenPetToday = true;
                }
                if(hasBeenPetToday){
                    stringBuilder.append("Has Your Animal Been Pet Today : true\n\n");
                }
                else{
                    stringBuilder.append("Has Your Animal Been Pet Today : false\n\n");
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
            animal.increaseFriendship(8);
            return new GameMessage(null,"The animal successfully moved");
        } else {
            if (animal.getType().isInCage()) {
                animal.setPosition(player.getFarm().getCoop().getPlacedTile().getPosition());
                animal.setOutside(false);
                return new GameMessage(null, "The animal entered the cage");
            } else {
                animal.setPosition(player.getFarm().getBarn().getPlacedTile().getPosition());
                animal.setOutside(false);
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
            return new GameMessage(null, "The animal must be in the coop or barn to feed hay");
        }
        animal.setLastFeedingTime(game.getTime());
        player.decreaseHay(2);
        return new GameMessage(null,"The animal successfully fed");
    }

    public GameMessage showHay(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        return new GameMessage(null,"Hay: "+player.getHay());
    }

    public GameMessage showProducts(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("These are all products: \n");
        if(player.getFarm().getBarn() != null){
            for (Animal animal : player.getFarm().getBarn().getAnimals()) {
                if(animal.doesProduce()){
                    stringBuilder.append(animal.getName()+" Produces:  ");
                    AnimalProduct animalProduct = animal.whichProduct();
                    animal.setCurrentProduct(animalProduct);
                    stringBuilder.append(animal.getCurrentProduct().getName());
                    stringBuilder.append("\n");
                }
            }
        }
        if(player.getFarm().getCoop() != null){
            for (Animal animal : player.getFarm().getCoop().getAnimals()) {
                if(animal.doesProduce()){
                    stringBuilder.append(animal.getName()+" Produces:  ");
                    AnimalProduct animalProduct = animal.whichProduct();
                    animal.setCurrentProduct(animalProduct);
                    stringBuilder.append(animal.getCurrentProduct().getName());
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
        Animal animal = null;
        if(player.getFarm().getBarn() != null){
            animal= player.getFarm().getBarn().getAnimalByName(animalName);
        }
        if (animal == null && player.getFarm().getCoop() != null) {
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
        }
        if (animal == null) {
            return new GameMessage(null, "There is no such animal");
        }

        if(animal.getCurrentProduct() != null){
            if(animal.getType().equals(AnimalType.cow) || animal.getType().equals(AnimalType.goat)){
                if(!player.getBackPack().checkItem(new WateringCan(ToolType.wateringCan),1)){
                    return new GameMessage(null, "There is no wateringCan");
                }
                player.decreaseEnergy(4);
                if(player.getBackPack().getCapacity()>=player.getBackPack().getMaxCapacity()){
                    return new GameMessage(null,"There is no space in your backpack");
                }

                player.getBackPack().addItem(animal.getCurrentProduct(),1);
                String output = animal.getCurrentProduct().getName();
                animal.setCurrentProduct(null);
                animal.increaseFriendship(5);
                return new GameMessage(null,output+" collected");
            }
            else if(animal.getType().equals(AnimalType.sheep)){
                if(!player.getBackPack().checkItem(new Tool(ToolType.shear),1)){
                    return new GameMessage(null, "There is no shear");
                }
                player.decreaseEnergy(4);
                if(player.getBackPack().getCapacity()>=player.getBackPack().getMaxCapacity()){
                    return new GameMessage(null,"There is no space in your backpack");
                }
                player.getBackPack().addItem(animal.getCurrentProduct(),1);
                animal.setCurrentProduct(null);
                animal.increaseFriendship(5);
                return new GameMessage(null,animal.getCurrentProduct().getName()+" collected");
            }
            else if(animal.getType().equals(AnimalType.pig)){
                if(animal.isOutside()){
                    if(player.getBackPack().getCapacity()>=player.getBackPack().getMaxCapacity()){
                        return new GameMessage(null,"There is no space in your backpack");
                    }
                    player.getBackPack().addItem(animal.getCurrentProduct(),1);
                    animal.setCurrentProduct(null);
                    return new GameMessage(null,animal.getCurrentProduct().getName()+" collected");
                }
            }
        }
        return new GameMessage(null,"There is no product");
    }

    public GameMessage sellAnimal(String animalName) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = app.getCurrentGame().getCurrentPlayer();
        Animal animal = player.getFarm().getBarn().getAnimalByName(animalName);
        if (animal != null) {
            int price = animal.calSellingPrice();
            player.addMoney(price);
            player.getFarm().getBarn().getAnimals().remove(animal);
            return new GameMessage(null,animal.getName()+" sold");
        }
        else{
            animal = player.getFarm().getCoop().getAnimalByName(animalName);
            if (animal != null) {
                int price = animal.calSellingPrice();
                player.addMoney(price);
                player.getFarm().getCoop().getAnimals().remove(animal);
                return new GameMessage(null,animal.getName()+" sold");
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
            case "Training": {
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if (item.getName().equals("FishingPole")) {
                        Tool tool = (Tool) item;
                        if (tool.getLevel() == 0) {
                            player.decreaseEnergy(8);
                            FishType type = generateRandomFish(game.getTime().getSeason(), false);
                            Fish fish = new Fish(type);
                            int count = fish.getFishingCount(game.getTodayWeather(), player.getSkill(SkillType.fishing).getLevel());
                            String quality = fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),"Training");
                            player.getBackPack().addItem(fish,count);
                            player.getSkill(SkillType.fishing).addAmount(5);
                            return new GameMessage(null, "You successfully got " + count + " of " + fish.getName()+" with quality: "+quality);
                        }
                    }
                }
                break;
            }
            case "Bamboo": {
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if (item.getName().equals("FishingPole")) {
                        Tool tool = (Tool) item;
                        if (tool.getLevel() == 1) {
                            player.decreaseEnergy(8);
                            FishType type = generateRandomFish(game.getTime().getSeason(), false);
                            Fish fish = new Fish(type);
                            int count = fish.getFishingCount(game.getTodayWeather(), player.getSkill(SkillType.fishing).getLevel());
                            String quality = fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),"Training");
                            player.getBackPack().addItem(fish,count);
                            player.getSkill(SkillType.fishing).addAmount(5);
                            return new GameMessage(null, "You successfully got " + count + " of " + fish.getName()+" with quality: "+quality);
                        }
                    }
                }
                break;
            }
            case "Fiberglass": {
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if (item.getName().equals("FishingPole")) {
                        Tool tool = (Tool) item;
                        if (tool.getLevel() == 2) {
                            player.decreaseEnergy(6);
                            FishType type = generateRandomFish(game.getTime().getSeason(), false);
                            Fish fish = new Fish(type);
                            int count = fish.getFishingCount(game.getTodayWeather(), player.getSkill(SkillType.fishing).getLevel());
                            String quality = fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),"Training");
                            player.getBackPack().addItem(fish,count);
                            player.getSkill(SkillType.fishing).addAmount(5);
                            return new GameMessage(null, "You successfully got " + count + " of " + fish.getName()+" with quality: "+quality);
                        }
                    }
                }
                break;
            }
            case "Iridium": {
                for (Item item : player.getBackPack().getItems().keySet()) {
                    if (item.getName().equals("FishingPole")) {
                        Tool tool = (Tool) item;
                        if (tool.getLevel() == 3) {
                            player.decreaseEnergy(4);
                            FishType type = generateRandomFish(game.getTime().getSeason(), true);
                            Fish fish = new Fish(type);
                            int count = fish.getFishingCount(game.getTodayWeather(), player.getSkill(SkillType.fishing).getLevel());
                            String quality = fish.getFishingQuality(game.getTodayWeather(),player.getSkill(SkillType.fishing).getLevel(),"Training");
                            player.getBackPack().addItem(fish,count);
                            player.getSkill(SkillType.fishing).addAmount(5);
                            return new GameMessage(null, "You successfully got " + count + " of " + fish.getName()+" with quality: "+quality);
                        }
                    }
                }
                break;
            }
            default: {
                return new GameMessage(null, "You need to choose a right fishing pole");
            }
        }
        return new GameMessage(null, "You don't access to this fishing pole in this level");
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
        ret += "One Time: " + (x == -1) + "\n";
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
        Plant plant = new Plant(plantType, new Time(game.getTime()));
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

            Time lastWatering = Time.maximum(Time.maximum(plant.getLastWateringTime(), p[1].getLastWateringTime())
                    , Time.maximum(p[2].getLastWateringTime(), p[3].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[1].getPlantingTime())
                    , Time.minimum(p[2].getPlantingTime(), p[3].getPlantingTime()));

            plant.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            p[1].setLastWateringTime(lastWatering);
            p[1].setPlantingTime(planting);
            p[2].setLastWateringTime(lastWatering);
            p[2].setPlantingTime(planting);
            p[3].setLastWateringTime(lastWatering);
            p[3].setPlantingTime(planting);

            ArrayList<Plant> giantPlants = new ArrayList<>(){{add(plant); add(p[1]); add(p[2]); add(p[3]);}};
            plant.setGianPlants(giantPlants);
            p[1].setGianPlants(giantPlants);
            p[2].setGianPlants(giantPlants);
            p[3].setGianPlants(giantPlants);

        } else if (flag[3] && flag[4] && flag[5]) {
            plant.setGianPosition(0);
            p[3].setGianPosition(1);
            p[4].setGianPosition(3);
            p[5].setGianPosition(2);

            Time lastWatering = Time.maximum(Time.maximum(plant.getLastWateringTime(), p[3].getLastWateringTime())
                    , Time.maximum(p[4].getLastWateringTime(), p[5].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[3].getPlantingTime())
                    , Time.minimum(p[4].getPlantingTime(), p[5].getPlantingTime()));

            plant.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            p[3].setLastWateringTime(lastWatering);
            p[3].setPlantingTime(planting);
            p[4].setLastWateringTime(lastWatering);
            p[4].setPlantingTime(planting);
            p[5].setLastWateringTime(lastWatering);
            p[5].setPlantingTime(planting);

            ArrayList<Plant> giantPlants = new ArrayList<>(){{add(plant); add(p[3]); add(p[4]); add(p[5]);}};
            plant.setGianPlants(giantPlants);
            p[3].setGianPlants(giantPlants);
            p[4].setGianPlants(giantPlants);
            p[5].setGianPlants(giantPlants);

        } else if (flag[5] && flag[6] && flag[7]) {
            plant.setGianPosition(1);
            p[5].setGianPosition(3);
            p[6].setGianPosition(2);
            p[7].setGianPosition(0);


            Time lastWatering = Time.maximum(Time.maximum(plant.getLastWateringTime(), p[5].getLastWateringTime())
                    , Time.maximum(p[6].getLastWateringTime(), p[7].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[5].getPlantingTime())
                    , Time.minimum(p[6].getPlantingTime(), p[7].getPlantingTime()));

            plant.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            p[5].setLastWateringTime(lastWatering);
            p[5].setPlantingTime(planting);
            p[6].setLastWateringTime(lastWatering);
            p[6].setPlantingTime(planting);
            p[7].setLastWateringTime(lastWatering);
            p[7].setPlantingTime(planting);

            ArrayList<Plant> giantPlants = new ArrayList<>(){{add(plant); add(p[5]); add(p[6]); add(p[7]);}};
            plant.setGianPlants(giantPlants);
            p[5].setGianPlants(giantPlants);
            p[6].setGianPlants(giantPlants);
            p[7].setGianPlants(giantPlants);

        } else if (flag[7] && flag[0] && flag[1]) {
            plant.setGianPosition(3);
            p[7].setGianPosition(2);
            p[0].setGianPosition(0);
            p[1].setGianPosition(1);


            Time lastWatering = Time.maximum(Time.maximum(plant.getLastWateringTime(), p[7].getLastWateringTime())
                    , Time.maximum(p[0].getLastWateringTime(), p[1].getLastWateringTime()));
            Time planting = Time.minimum(Time.minimum(plant.getPlantingTime(), p[7].getPlantingTime())
                    , Time.minimum(p[0].getPlantingTime(), p[1].getPlantingTime()));

            plant.setLastWateringTime(lastWatering);
            plant.setPlantingTime(planting);
            p[7].setLastWateringTime(lastWatering);
            p[7].setPlantingTime(planting);
            p[0].setLastWateringTime(lastWatering);
            p[0].setPlantingTime(planting);
            p[1].setLastWateringTime(lastWatering);
            p[1].setPlantingTime(planting);

            ArrayList<Plant> giantPlants = new ArrayList<>(){{add(plant); add(p[7]); add(p[0]); add(p[1]);}};
            plant.setGianPlants(giantPlants);
            p[7].setGianPlants(giantPlants);
            p[0].setGianPlants(giantPlants);
            p[1].setGianPlants(giantPlants);
        }
        player.getSkill(SkillType.farming).addAmount(5);
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
        ret += "Last Watering Time: " + plant.getLastWateringTime() + "\n";
        ret += "Planting Time: " + plant.getPlantingTime();
        return new GameMessage(null, ret);
    }

    public GameMessage showFriendships(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        for (FriendShip friendShip : player.getFriendShips()) {
            stringBuilder.append("Your friendship with: "+friendShip.getPlayer().getUser().getUsername()+"   "+"xp: "+friendShip.getXp()+"\n");
        }
        return new GameMessage(null, stringBuilder.toString());
    }

    public GameMessage talk(String username , String message) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username!");
        }
        if(player.getUser().getUsername().equals(username)) {
            return new GameMessage(null, "You can not talk to your self!");
        }
//        if(!(Math.abs(player2.getPosition().x - player.getPosition().x) <= 1 && Math.abs(player2.getPosition().y - player.getPosition().y) <= 1)) {
//            return new GameMessage(null, "You aren't close enough to talk!");
//        }
        player.getFriendShipByPlayer(player2).addMessage(message);
        player2.getFriendShipByPlayer(player).addMessage(message);
        player.getFriendShipByPlayer(player2).increaseXp(20);
        player2.getFriendShipByPlayer(player).increaseXp(20);
        return new GameMessage(null,"You successfully talked with " + username + "!");
    }

    public GameMessage talkHistory(String username){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : player.getFriendShipByPlayer(player2).getMessageLog()) {
            stringBuilder.append(s);
            stringBuilder.append("\n");
        }
        return new GameMessage(null, "Here is your talk history with "+username+" :\n"+stringBuilder.toString());
    }

    public GameMessage gifting(String username ,String itemName,int amount) {
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username!");
        }
        if(player.getFriendShipByPlayer(player2).getLevel() < 1){
            return new GameMessage(null, "You can't gift in this level");
        }
//        if(!(Math.abs(player2.getPosition().x - player.getPosition().x) <= 1 && Math.abs(player2.getPosition().y - player.getPosition().y) <= 1)) {
//            return new GameMessage(null, "You aren't close enough");
//        }
        Item item=player.getBackPack().getItemByName(itemName);
        if(item == null) {
            return new GameMessage(null, "There is no item with that name in your backpack!");
        }
        if(player.getBackPack().getItemCount(item) < amount) {
            return new GameMessage(null, "You don't have enough of this item in your backpack");
        }
        player.getBackPack().removeItem(item, amount);
        player2.getBackPack().addItem(item, amount);
        player2.getFriendShipByPlayer(player).addGift(new Gift(item, amount));
        player2.getFriendShipByPlayer(player).addMessage("__You have received "+amount+" "+itemName+" from "+player.getUser().getUsername()+" as a gift__");
        return new GameMessage(null,"You successfully gifted "+username);
    }

    public GameMessage ratingGift(String userName,int number,int rate){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(userName);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username in this game!");
        }
        if(player.getFriendShipByPlayer(player2).getGiftLog().size() < number) {
            return new GameMessage(null, "There is no gift with this number");
        }
        Gift gift=player.getFriendShipByPlayer(player2).getGiftLog().get(number-1);
        if(gift.getRate() != 0) {
            return new GameMessage(null,"You have already rated this gift");
        }
        if(rate >5 || rate < 1) {
            return new GameMessage(null, "You must enter a number between 1 and 5");
        }
        if(rate > 3){
            gift.setRate(rate);
            player2.getFriendShipByPlayer(player).increaseXp(((rate-3)*30) + 15);
            player.getFriendShipByPlayer(player2).increaseXp(((rate-3)*30) + 15);
        }
        else{
            gift.setRate(rate);
            player2.getFriendShipByPlayer(player).decreaseXp(((3-rate)*30) + 15);
            player.getFriendShipByPlayer(player2).decreaseXp(((3-rate)*30) + 15);
        }
        return new GameMessage(null,"You successfully rated "+rate);
    }

    public GameMessage listingGift(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        for (FriendShip friendShip : player.getFriendShips()) {
            for (Gift gift : friendShip.getGiftLog()) {
                stringBuilder.append(friendShip.getPlayer().getUser().getUsername()+" :\n");
                stringBuilder.append(gift.getItem().getName()+"\n");
            }
        }
        return new GameMessage(null,"You have received :"+stringBuilder.toString());
    }

    public GameMessage giftHistory(String username){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username in this game!");
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Gift gift : player.getFriendShipByPlayer(player2).getGiftLog()) {
            stringBuilder.append(gift.getItem().getName()+"\n");
        }
        return new GameMessage(null,"You have received :"+stringBuilder.toString());
    }

    public GameMessage hugging(String username){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username in this game!");
        }
        if(player.getFriendShipByPlayer(player2).getLevel() < 2) {
            return new GameMessage(null, "You can't hug in this level");
        }
//        if(!(Math.abs(player2.getPosition().x - player.getPosition().x) <= 1 && Math.abs(player2.getPosition().y - player.getPosition().y) <= 1)) {
//            return new GameMessage(null, "You aren't close enough");
//        }
        player2.getFriendShipByPlayer(player).increaseXp(60);
        player.getFriendShipByPlayer(player2).increaseXp(60);
        return new GameMessage(null,"You successfully hugged "+username);
    }

    public GameMessage givingFlower(String username){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);
        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username in this game!");
        }
        if(player.getFriendShipByPlayer(player2).getLevel() < 2) {
            return new GameMessage(null, "You can't gift flower in this level");
        }
        Item item =new Material(MaterialType.bouquet);
        if(!player.getBackPack().checkItem(item,1)){
            return new GameMessage(null, "You don't have a bouquet");
        }
        player.getBackPack().removeItem(item,1);
        player2.getBackPack().addItem(item,1);
        player.getFriendShipByPlayer(player2).setLevel(3);
        player2.getFriendShipByPlayer(player).setLevel(3);
        return new GameMessage(null,"You successfully gifted "+username+" a flower");
    }

    public GameMessage marriage(String username){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Player player2 = game.getPlayerByUsername(username);

        if(player2 == null) {
            return new GameMessage(null, "There is no player with that username in this game!");
        }
        Item item = player.getBackPack().getItemByName(new Material(MaterialType.weddingRing).getName());
        if(item == null) {
            return new GameMessage(null, "you must have a wedding ring");
        }
        if(player.getUser().getGender().equals(player2.getUser().getGender())) {
           return new GameMessage(null, "You can't marriage with your gender");
        }
        if(player.getFriendShipByPlayer(player2).getLevel() <3){
            return new GameMessage(null, "You can't marriage in this level");
        }
        player2.getFriendShipByPlayer(player).addMarriageRequest(player.getUser().getUsername()+" has asked you for marriage!");
        return new GameMessage(null,"Now you should wait for the other player to answer");
    }

    public GameMessage marriageRequests(){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("These are marriages requested by the following players:\n");
        for (FriendShip friendShip : player.getFriendShips()) {
            if(friendShip.getMarriageRequest() != null) {
                stringBuilder.append(friendShip.getMarriageRequest()+"\n");
            }
        }
        return new GameMessage(null,stringBuilder.toString());
    }

    public GameMessage answeringMarriage(String username,boolean accepted){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player answer = game.getCurrentPlayer();
        Player ask = game.getPlayerByUsername(username);
        answer.getFriendShipByPlayer(ask).addMarriageRequest(null);
        if(!accepted){
            ask.getFriendShipByPlayer(answer).setLevel(0);
            answer.getFriendShipByPlayer(ask).setLevel(0);
            return new GameMessage(null,"You rejected "+ask.getUser().getUsername());
        }
        ask.getFriendShipByPlayer(answer).setLevel(4);
        answer.getFriendShipByPlayer(ask).setLevel(4);
        ask.getBackPack().removeItem(new Material(MaterialType.weddingRing),1);
        answer.getBackPack().addItem(new Material(MaterialType.weddingRing),1);
        return new GameMessage(null,"You accepted "+ask.getUser().getUsername());
    }

    public GameMessage enterStore(String StoreName){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Store store = Store.getStoreByName(StoreName);

        if(store == null){
            return new GameMessage(null, "There is no store with that name");
        }
        if(game.getTime().getHour() > store.getClosingTime() || game.getTime().getHour() < store.getOpeningTime()){
            return new GameMessage(null,"You can't enter a store in this hour");
        }
        if(player.getCurrentStore()!=null){
            return new GameMessage(null, "You are already in a store!");
        }
        if(store.isNear(player.getPosition())){
            return new GameMessage(null, "You are not close enough to store!");
        }
        player.setCurrentStore(store);
        return new GameMessage(null,"You successfully entered "+StoreName);
    }

    public GameMessage exitStore(String StoreName){
        App app = App.getInstance();
        Game game = app.getCurrentGame();
        Player player = game.getCurrentPlayer();
        Store store = Store.getStoreByName(StoreName);
        if(store == null){
            return new GameMessage(null, "There is no store with that name");
        }
        if(player.getCurrentStore()==null){
            return new GameMessage(null, "You are not in any store");
        }
        if(!player.getCurrentStore().getName().equals(StoreName)){
            return new GameMessage(null, "You are not int this store");
        }
        player.setCurrentStore(null);
        return new GameMessage(null,"You successfully exited "+StoreName);
    }

    public GameMessage shippingBinSell(String itemName, int count){
        Game game = App.getInstance().getCurrentGame();
        Player player = game.getCurrentPlayer();
        ShippingBin shippingBin = null;
        for(int i = -1; i <= 1; i++){
            for(int j = -1; j <= 1; j++){
                if(i == 0 & j == 0){
                    continue;
                }
                Tile tile = game.getMap().getTile(new Position(i, j));
                if(tile != null){
                    TileObject tileObject = tile.getObject();
                    if(tileObject instanceof ShippingBin){
                        shippingBin = (ShippingBin) tileObject;
                    }
                }
            }
        }
        if(shippingBin == null){
            return new GameMessage(null, "There is no shipping bin");
        }
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        for (java.util.Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            Integer value = entry.getValue();
            if(item.toString().equals(itemName)){
                if(value < count){
                    return new GameMessage(null, "You don't have this amount of this item!");
                }
                if(value == count){
                    player.setFeatureMoney((int) (item.getPrice() * count * shippingBin.getRefund()));
                    backPack.getItems().remove(item);
                    return new GameMessage(null, "You have been successfully selled " + itemName);
                }
                player.setFeatureMoney((int) (item.getPrice() * count * shippingBin.getRefund()));
                backPack.removeItem(item, count);
                return new GameMessage(null, "You have been successfully selled " + itemName);
//                if(item.getPrice() == 0){
//                    return new GameMessage(null, "You can't sell this item!");
//                }

            }
        }
        return new GameMessage(null, "You don't have this item!");
    }

    @Override
    public Message exit() {
        return null;
    }
}