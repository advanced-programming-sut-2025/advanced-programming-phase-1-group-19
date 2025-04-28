package Controllers;

import Modules.*;
import Modules.Enums.*;
import Modules.Interactions.Messages.*;
import Modules.Map.*;
import Modules.Tools.BackPack;
import Modules.Tools.Tool;
import Views.*;
import java.util.Map;
import java.util.ArrayList;
public class GameController extends Controller {
    private static GameController instance;
    private GameController() {}
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    @Override
    public GameMessage showCurrentMenu() {
        App app = App.getInstance();
        app.setCurrentMenu(Menu.MainMenu);
        return new GameMessage(null, "You are now in main menu");
    }

    private final App app = App.getInstance();

    private boolean hasActiveGame(User user) {
        return user.getCurrentGame() != null;
    }

    private boolean isMapIdValid(int mapId) {
    }

    private boolean hasEnoughEnergy(int amount) {}

    public GameMessage startNewGame(String[] usernames) {
        if(usernames.length > 3) {
            return new GameMessage(null, "Too many players!");
        }
        User[] users = new User[usernames.length + 1];
        users[0] = app.getCurrentUser();
        for(int i = 0; i < usernames.length; i++) {
            User user = app.getUserByUsername(usernames[i]);
            if(user == null) {
                return new GameMessage(null, "User " + usernames[i] + " not found!");
            }
            if(hasActiveGame(user)) {
                return new GameMessage(null, "User " + usernames[i] + " is already in a game!");
            }
            users[i+1] = user;
        }
        GameMenu menu = GameMenu.getInstance();
        String mapsPreview = "Users are set successfully, here are the available maps.\n";
        for(FarmMap farmMap : FarmMap.values()) {
            mapsPreview += farmMap.name() + " map:\n";
            mapsPreview += farmMap.printMap() + "\n";
        }
        mapsPreview += "Press ENTER to continue...";
        menu.getSingleLine(mapsPreview);
        int[] mapIDs = new int[users.length];
        String error = "";
        for(int i = 0; i < users.length; i++) {
            String idString = menu.getSingleLine(error + "choose map for user " + users[i].getUsername());
            error = "";
            if(!idString.matches("[1-4]")) {
                error = "Wrong format, first map was selected!\n";
                mapIDs[i] = 1;
            }
            else {
                mapIDs[i] = Integer.parseInt(idString);
            }
        }
        ArrayList<Player> players = new ArrayList<>();
        ArrayList<Farm> farms = new ArrayList<>();
        for(int i = 0; i < users.length; i++) {
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

        for(User user : users) {
            user.setCurrentGame(game);
        }

        return new GameMessage(null, error + "New game created successfully!!!");
    }

    public GameMessage loadGame() {}

    public GameMessage exitGame() {
        App app = App.getInstance();
        if(!app.getCurrentGameStarter().equals(app.getCurrentUser())){
            return new GameMessage(null, "you can't exit game!");
        }
        // TODO: save game
        app.setCurrentGame(null);
        return new GameMessage(null, "exited game successfully!");
    }

    public GameMessage forceTerminate() {
        GameMenu gameMenu = GameMenu.getInstance();
        boolean quit = true;
        for(int i = 0; i < App.getInstance().getCurrentGame().getPlayers().size() - 1; i++){
            String input;
            do{
                input = gameMenu.getSingleLine("");
                if(input.matches("^\\s*n\\s*$")){
                    quit = false;
                }
                else if(input.matches("^\\s*y\\s*$")){

                }
                else{
                    System.out.println("invalid vote");
                }
            }
            while (!input.matches("^\\s*n\\s*$") && !input.matches("^\\s*y\\s*$"));
        }
        if(quit){
            for (Player player : App.getInstance().getCurrentGame().getPlayers()) {
                player.getUser().setCurrentGame(null);
            }
            App.getInstance().getGames().remove(App.getInstance().getCurrentGame());
            App.getInstance().setCurrentGame(null);
            App.getInstance().setCurrentGameStarter(null);
            return new GameMessage(null, "Game has been terminated!");
        }
        else{
            return new GameMessage(null, "Not enough votes");
        }
    }

    public GameMessage nextTurn() {
       Game game = App.getInstance().getCurrentGame();
        do{
            game.setNextPlayer();
        }while(game.getCurrentPlayer().isFainted());
        return new GameMessage(null, "next turn");
    }

    public GameMessage showTime(){
        int hour = App.getInstance().getCurrentGame().getTime().getHour();
        return new GameMessage(null, String.valueOf(hour));
    }

    public GameMessage showDate(){
        Time time = App.getInstance().getCurrentGame().getTime();
        int date = time.getDay();
        Season season = time.getSeason();
        return new GameMessage(null, date + " " + season.toString());
    }

    public GameMessage showDateTime(){
        Time time = App.getInstance().getCurrentGame().getTime();
        int hour = time.getHour();
        int date = time.getDay();
        Season season = time.getSeason();
        return new GameMessage(null, hour + " " + date + " " + season.toString());
    }

    public GameMessage showDayOfWeak(){
        return new GameMessage(null, App.getInstance().getCurrentGame().getTime().calculateWeekDay());
    }

    public GameMessage showSeason(){
        Time time = App.getInstance().getCurrentGame().getTime();
        Season season = time.getSeason();
        return new GameMessage(null, season.toString());
    }

    public GameMessage cheatAdvanceTime(int x){
        for (int i = 0; i < x; i++)
            App.getInstance().getCurrentGame().nextHour();
        return new GameMessage(null, "time traveled " + x + " hours");
    }

    public GameMessage cheatAdvanceDate(int x){
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
        if(start.equals(end)){
            return new GameMessage(null, "Dude you are already there :/");
        }
        ArrayList<Tile> path = map.getPath(start, end);
        if(path == null){
            return new GameMessage(null, "Ops, sorry you cant go there");
        }
        int moves = 0;
        String faintMessage = "";
        for(Tile tile : path){
            player.setPosition(tile.getPosition());
            moves++;
            if(moves % 20 == 0) {
//                TODO: reduce energy by one;
//                TODO: go to next person and faint if energy == 0
//                TODO: set faintMessage to "Oh you have fainted middle way :(\n"
            }
        }
        return new GameMessage(null, faintMessage + "Your current position is (" + player.getPosition().x + ", " + player.getPosition().y + ")");
    }
    public GameMessage cheatForecast(String weather) {
        if(Weather.getWeatherType(weather) == null){
            return new GameMessage(null, "not such weather type");
        }
        App.getInstance().getCurrentGame().setTomorrowWeather(Weather.getWeatherType(weather));
        return new GameMessage(null, "you successfully cheated weather forecast!");
    }

    public GameMessage showEnergy(){
        App app = App.getInstance();
        return new GameMessage(null,"Your energy is: "+ app.getCurrentGame().getCurrentPlayer().getEnergy().getAmount());
    }

    public GameMessage showInventory(){
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
        return new GameMessage(null, "Your BackPack: \n"+stringBuilder.toString());
    }

    public GameMessage inventoryTrash(String itemName,int number,boolean delete){
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        if(delete){
            for (Item item : player.getBackPack().getItems().keySet()) {
                if(item.getName().equals(itemName)) {
                    player.getBackPack().getItems().remove(item);
                    break;
                }
            }
            return new GameMessage(null,"You fully trashed item "+itemName);
        }
        for (Item item : player.getBackPack().getItems().keySet()) {
            if (item.getName().equals(itemName)) {
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

    public GameMessage cheatEnergySet(int amount){
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.addEnergy(amount-player.getEnergy().getAmount());
        return new GameMessage(null,"New energy set to "+amount);
    }

    public GameMessage unlimitedEnergySet(){
        App app = App.getInstance();
        Player player = app.getCurrentGame().getCurrentPlayer();
        player.getEnergy().setUnlimited(true);
        return new GameMessage(null,"Now your energy is unlimited");
    }

    public GameMessage buildGreenHouse() {
//        TODO: check if we have enough coin and wood!

    }

    public GameMessage equipTool(String toolName) {
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        boolean toolFound = false;
        for (Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            // only one tool of each type!
            if(item.getName().equals(toolName)){
                App.getInstance().getCurrentGame().getCurrentPlayer().setCurrentTool((Tool) item);
                toolFound = true;
                break;
            }
        }
        if(!toolFound){
            return new GameMessage(null, "you don't have that tool!")
        }
        else{
            return new GameMessage(null, "You have equipped " + toolName);
        }
    }

    public GameMessage showCurrentTool(){
        Tool tool = App.getInstance().getCurrentGame().getCurrentPlayer().getCurrentTool();
        if(tool == null){
            return new GameMessage(null, "you don't have equipped tool!");
        }
        else{
            return new GameMessage(null, "You have equipped " + tool.getName());
        }
    }

    public GameMessage showAllTools(){
        BackPack backPack = App.getInstance().getCurrentGame().getCurrentPlayer().getBackPack();
        StringBuilder stringBuilder = new StringBuilder("All tools: \n");
        for (Map.Entry<Item, Integer> entry : backPack.getItems().entrySet()) {
            Item item = entry.getKey();
            if(item instanceof Tool tool){
                stringBuilder.append("Tool: ").append(tool.getName()).append("\n");
            }
        }
        stringBuilder.deleteCharAt(stringBuilder.length()-1);
        return new GameMessage(null,stringBuilder.toString());
    }

    public GameMessage upgradeTool(String toolName){
        // TODO:check if in blacksmith!
        // TODO: check if enough money and energy

    }
    public GameMessage printMap(Position position, int size) {
        if(size > 80) {
            return new GameMessage(null, "please use sizes smaller than 100");
        }
        Game game = App.getInstance().getCurrentGame();
        Map map = (Map) game.getMap();
        char[][] all = new char[size][size];
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                char c = ' ';
                Tile tile = map.getTile(new Position(position.x + i, position.y + j));
                if(tile != null){
                    Building building = tile.getBuilding();
                    if(building == null){
                        c = '.';
                    }
                    else if(building instanceof House) {
                        c = 'H';
                    }
                    else if(building instanceof GreenHouse) {
                        c = 'G';
                    }
                    else if(building instanceof Lake) {
                        c = 'L';
                    }
                    else if(building instanceof Quarry) {
                        c = 'Q';
                    }
                }
                all[i][j] = c;
            }
        }
        StringBuilder ret = new StringBuilder();
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
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

    }


    @Override
    public Message exit() {
//        TODO: save game and go back to main Menu
    }
}
