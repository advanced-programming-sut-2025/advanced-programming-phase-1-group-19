package Views;

import Modules.Interactions.Commands.MainCommand;
import Modules.Interactions.Messages.MainMessage;

public class MainMenu implements AppMenu {
    private static MainMenu instance;

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private MainMessage runCommand(MainCommand command, String input) {

    }

    @Override
    public void checkCommand() {

    }

}
