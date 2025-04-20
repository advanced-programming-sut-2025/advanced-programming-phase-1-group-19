package Views;

import Modules.Interactions.Commands.MainCommand;
import Modules.Interactions.Messages.MainMessage;

import java.util.Scanner;

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

    private final AppView appView = AppView.getInstance();

    @Override
    public void checkCommand() {
        Scanner scanner = appView.getScanner();
    }

}
