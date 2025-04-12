package Views;

import Modules.Interactions.Commands.ProfileCommand;
import Modules.Interactions.Messages.ProfileMessage;

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

    @Override
    public void checkCommand() {

    }
}
