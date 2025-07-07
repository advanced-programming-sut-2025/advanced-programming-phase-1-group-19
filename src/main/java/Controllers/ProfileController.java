package Controllers;

import Models.App;
import Models.Enums.Menu;
import Models.Interactions.Messages.MainMessage;
import Models.Interactions.Messages.Message;
import Models.Interactions.Messages.ProfileMessage;
import Models.Interactions.Messages.RegistrationMessage;
import Models.User;

public class ProfileController extends Controller implements UserInfoController{
    private static ProfileController instance;
    private ProfileController() {}
    public static ProfileController getInstance() {
        if (instance == null) {
            instance = new ProfileController();
        }
        return instance;
    }

    @Override
    public ProfileMessage showCurrentMenu() {
        return new ProfileMessage(null, Menu.ProfileMenu.toString());
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

    public ProfileMessage changePassword(String newPassword, String oldPassword) {
        App app = App.getInstance();
        if (!isPasswordValid(newPassword)) {
            return new ProfileMessage(null, "Password format is invalid");
        }

        if (!doesPasswordHaveUpperCase(newPassword)) {
            return new ProfileMessage(null, "Password doesn't have uppercase letters'");
        }

        if (!doesPasswordHaveLowerCase(newPassword)) {
            return new ProfileMessage(null, "Password doesn't have lowercase letters'");
        }

        if (!doesPasswordHaveNumber(newPassword)) {
            return new ProfileMessage(null, "Password doesn't have digits");
        }

        if (!doesPasswordHaveSpecialChar(newPassword)) {
            return new ProfileMessage(null, "Password doesn't have special characters");
        }
        String codedOldPassword = sha256(oldPassword);
        String codedNewPassword = sha256(newPassword);
        if(!app.getCurrentUser().getPassword().equals(codedOldPassword)) {
            return new ProfileMessage(null,"Password is invalid");
        }
        if(newPassword.equals(oldPassword)) {
            return new ProfileMessage(null,"Your password must be different");
        }
        app.getCurrentUser().setPassword(codedNewPassword);
        return new ProfileMessage(null,"Password successfully changed");
    }

    public ProfileMessage showUserInfo() {
        App app = App.getInstance();
        User currentUser = app.getCurrentUser();
        String ret = "";
        ret += "Username: " + currentUser.getUsername() + "\n";
        ret += "Nickname: " + currentUser.getNickname() + "\n";
        ret += "Max Money: " + currentUser.getMaxMoney() + "\n";
        ret += "Games Count: " + currentUser.getGamesCount();
        return new ProfileMessage(null, ret);
    }

    @Override
    public ProfileMessage exit() {
        App app = App.getInstance();
        app.setCurrentMenu(Menu.MainMenu);
        return new ProfileMessage(null, "You are now in main menu");
    }
}
