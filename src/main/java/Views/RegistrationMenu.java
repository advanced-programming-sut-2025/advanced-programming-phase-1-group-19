package Views;

import Modules.Interactions.Commands.RegistrationCommand;
import Modules.Interactions.Messages.RegistrationMessage;

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

    @Override
    public void checkCommand() {
//        TODO: baraye forget password ask aboo
    }
}
