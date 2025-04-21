package Controllers;

import Modules.App;
import Modules.Game;
import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Map.Farm;
import Modules.Map.FarmMap;
import Modules.Map.Map;
import Modules.Map.Position;
import Modules.Player;
import Modules.User;
import Views.GameMenu;

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
        User[] users = new User[usernames.length];
        for(int i = 0; i < usernames.length; i++) {
            User user = app.getUserByUsername(usernames[i]);
            if(user == null) {
                return new GameMessage(null, "User " + usernames[i] + " not found!");
            }
            if(hasActiveGame(user)) {
                return new GameMessage(null, "User " + usernames[i] + "is already in a game!");
            }
            users[i] = user;
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
                error = "Wrong format, map number one was selected!\n";
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

    public GameMessage exitGame() {}

    public GameMessage forceTerminate(boolean[] votes) {}

    public GameMessage nextTurn() {
    }

    public GameMessage showTime(){}

    public GameMessage showDate(){}

    public GameMessage showDateTime(){}

    public GameMessage showDayOfWeak(){}

    public GameMessage showSeason(){}

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
