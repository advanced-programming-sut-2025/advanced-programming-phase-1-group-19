package Controllers;

import Modules.*;
import Modules.Enums.*;
import Modules.Interactions.Messages.*;
import Modules.Map.*;
import Views.*;

import java.util.Scanner;

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
            // TODO: user.currentGame == null
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
    public GameMessage cheatThor(int x, int y) {}

    public GameMessage showWeather() {}

    public GameMessage walk(Position position) {}

    public GameMessage printMap(Position position, int size) {}

    public GameMessage helpPrintMap() {}

    public GameMessage openHouseMenu() {

    }


    @Override
    public Message exit() {
//        TODO: save game and go back to main Menu
    }
}
