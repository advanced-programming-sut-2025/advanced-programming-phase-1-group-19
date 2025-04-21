package Controllers;

import Modules.*;
import Modules.Enums.Season;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Map.Position;
import Views.AppView;

import java.util.Scanner;

import static Modules.Enums.Menu.GameMenu;

public class GameController extends Controller {
    private static GameController instance;
    private GameController() {}
    public static GameController getInstance() {
        if (instance == null) {
            instance = new GameController();
        }
        return instance;
    }

    private boolean hasActiveGame(User user) {
        return user.getCurrentGame() != null;
    }

    private boolean isMapIdValid(int mapId) {
    }

    private boolean hasEnoughEnergy(int amount) {}

    public GameMessage startNewGame(String[] usernames) {}

    public GameMessage chooseMap(int mapID) {}

    public GameMessage loadGame() {}

    public GameMessage exitGame() {
        App app = App.getInstance();
        if(!app.getCurrentGameStarter().equals(app.getCurrentUser())){
            return new GameMessage(null, "you can't exit game!");
        }
        else{
            // TODO: save game
            app.setCurrentGame(null);
        }
    }

    public GameMessage forceTerminate() {
        Scanner scanner = AppView.getInstance().getScanner();
        boolean quit = true;
        for(int i = 0; i < App.getInstance().getCurrentGame().getPlayers().size() - 1; i++){
            String input;
            do{
                input = scanner.nextLine();
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
