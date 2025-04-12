package Controllers;

import Modules.Interactions.Messages.Message;
import Modules.Interactions.Messages.ProfileMessage;

public class ProfileController extends Controller implements UserInfoController{
    private static ProfileController instance;
    private ProfileController() {}
    public static ProfileController getInstance() {
        if (instance == null) {
            instance = new ProfileController();
        }
        return instance;
    }

    public ProfileMessage changeUsername(String newUsername) {}

    public ProfileMessage changeNickname(String newNickname) {}

    public ProfileMessage changeEmail(String email) {}

    public ProfileMessage changePassword(String newPassword, String OldPassword) {}

    public ProfileMessage showUserInfo() {}

    @Override
    public Message exit() {
//        TODO: back to main menu
    }
}
