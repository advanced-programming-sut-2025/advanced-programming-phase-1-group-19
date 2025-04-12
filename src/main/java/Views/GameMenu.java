package Views;

import Modules.Interactions.Commands.GameCommand;
import Modules.Interactions.Messages.GameMessage;

public class GameMenu implements AppMenu {
    private static GameMenu instance;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null) {
            instance = new GameMenu();
        }
        return instance;
    }

    private GameMessage runCommand(GameCommand command, String input) {
//        TODO: for force terminate get all votes from users and send to controller
    }

    @Override
    public void checkCommand() {

    }
}
