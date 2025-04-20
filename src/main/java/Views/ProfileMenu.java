package Views;

import Modules.Interactions.Commands.ProfileCommand;
import Modules.Interactions.Messages.ProfileMessage;

import java.util.Scanner;

public class ProfileMenu implements AppMenu {
    private static ProfileMenu instance;

    private ProfileMenu() {
    }

    public static ProfileMenu getInstance() {
        if (instance == null) {
            instance = new ProfileMenu();
        }
        return instance;
    }

    private ProfileMessage runCommand(ProfileCommand command, String input) {

    }

    private final AppView appView = AppView.getInstance();

    @Override
    public void checkCommand() {
        Scanner scanner = appView.getScanner();
    }
}
