package Controllers;

import Modules.Interactions.Messages.Message;
import Modules.Interactions.Messages.RegistrationMessage;

public class RegistrationController extends Controller implements UserInfoController{
    private static RegistrationController instance;
    private RegistrationController() {}
    public static RegistrationController getInstance() {
        if (instance == null) {
            instance = new RegistrationController();
        }
        return instance;
    }

    private String generateRandomPassword() {}

    public RegistrationMessage Register (String username, String password, String passwordConfirm, String nickName, String email, String gender) {}

    public RegistrationMessage acceptRandomPassword(boolean accept) {}

    public RegistrationMessage pickQuestion(int id, String answer, String answerConfirm) {}

    public RegistrationMessage login(String username, String password, boolean stayLoggedIn) {}

    public RegistrationMessage forgetPassword(String username) {}

    public RegistrationMessage checkAnswer(String username, String answer) {
    }

    @Override
    public Message exit() {
//        TODO: exit app
    }
}
