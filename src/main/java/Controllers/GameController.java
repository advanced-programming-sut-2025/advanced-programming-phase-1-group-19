package Controllers;

import Modules.Interactions.Messages.GameMessage;
import Modules.Interactions.Messages.Message;
import Modules.Map.Position;
import Modules.User;

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

    private boolean isMapIdValid(int mapId) {}

    private boolean hasEnoughEnergy(int amount) {}

    public GameMessage startNewGame(String[] usernames) {}

    public GameMessage chooseMap(int mapID) {}

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
