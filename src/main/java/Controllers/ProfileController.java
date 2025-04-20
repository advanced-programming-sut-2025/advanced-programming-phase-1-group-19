package Controllers;

import Modules.App;
import Modules.Interactions.Messages.Message;
import Modules.Interactions.Messages.ProfileMessage;
import Modules.User;

public class ProfileController extends Controller implements UserInfoController{
    private static ProfileController instance;
    private ProfileController() {}
    public static ProfileController getInstance() {
        if (instance == null) {
            instance = new ProfileController();
        }
        return instance;
    }

    public ProfileMessage changeUsername(String newUsername) {
        App app = App.getInstance();
        if(isUsernameTaken(newUsername)) {
            return new ProfileMessage(null,"Username is already taken");
        }
        if(!isUsernameValid(newUsername)) {
            return new ProfileMessage(null,"Username is invalid");
        }
        if(app.getCurrentUser().getUsername().equals(newUsername)) {
            return new ProfileMessage(null,"New username must be different");
        }
        app.getCurrentUser().setUsername(newUsername);
        return new ProfileMessage(null,"Username successfully changed");
    }

    public ProfileMessage changeNickname(String newNickname) {
        App app = App.getInstance();
        if(app.getCurrentUser().getNickname().equals(newNickname)) {
            return new ProfileMessage(null,"Your new nickname must be different");
        }
        app.getCurrentUser().setNickname(newNickname);
        return new ProfileMessage(null,"Nickname successfully changed");
    }

    public ProfileMessage changeEmail(String email) {
        App app = App.getInstance();
        if(!isEmailValid(email)) {
            return new ProfileMessage(null,"Email is invalid");
        }
        if(app.getCurrentUser().getEmail().equals(email)) {
            return new ProfileMessage(null,"Your email must be different");
        }
        app.getCurrentUser().setEmail(email);
        return new ProfileMessage(null,"Email successfully changed");
    }

    public ProfileMessage changePassword(String newPassword, String OldPassword) {
        App app = App.getInstance();
        if(!isPasswordValid(newPassword)) {
            return new ProfileMessage(null,"Password format is invalid");
        }
        if(newPassword.equals(OldPassword)) {
            return new ProfileMessage(null,"Your password must be different");
        }
        if(!app.getCurrentUser().getPassword().equals(newPassword)) {
            return new ProfileMessage(null,"Password is invalid");
        }
        app.getCurrentUser().setPassword(newPassword);
        return new ProfileMessage(null,"Password successfully changed");
    }

    public ProfileMessage showUserInfo() {
        App app = App.getInstance();
        User currentUser = app.getCurrentUser();
        return new ProfileMessage(null,currentUser.getUsername()+"\n"+currentUser.getNickname()+"\n"
                +currentUser.getMaxMoney()+"\n"+currentUser.getGamesCount());
    }

    @Override
    public Message exit() {
//        TODO: back to main menu
    }
}
