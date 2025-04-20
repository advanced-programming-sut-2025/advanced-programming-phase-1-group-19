package Views;

import Modules.Interactions.Commands.RegistrationCommand;
import Modules.Interactions.Messages.RegistrationMessage;

import java.util.Scanner;

public class RegistrationMenu implements AppMenu {
    private static RegistrationMenu instance;

    private RegistrationMenu() {
    }

    public static RegistrationMenu getInstance() {
        if (instance == null) {
            instance = new RegistrationMenu();
        }
        return instance;
    }

    private RegistrationMessage runCommand(RegistrationCommand command, String input) {

    }

    private final AppView appView = AppView.getInstance();

    @Override
    public void checkCommand() {
        Scanner scanner = appView.getScanner();
//        TODO: baraye forget password ask aboo
    }
}
