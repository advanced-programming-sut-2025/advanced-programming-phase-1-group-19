package Controllers;

import Modules.App;
import Modules.Enums.Menu;
import Modules.Enums.Question;
import Modules.Interactions.Commands.RegistrationCommand;
import Modules.Interactions.Messages.Message;
import Modules.Interactions.Messages.RegistrationMessage;
import Modules.User;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class RegistrationController extends Controller implements UserInfoController {
    private static RegistrationController instance;

    private RegistrationController() {
    }

    public static RegistrationController getInstance() {
        if (instance == null) {
            instance = new RegistrationController();
        }
        return instance;
    }

    @Override
    public RegistrationMessage showCurrentMenu() {
        return new RegistrationMessage(null, Menu.RegistrationMenu.toString());
    }

    private String generateRandomPassword() {
        Random randomLength = new Random();
        int length = randomLength.nextInt(10) + 8;
        String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
        String DIGITS = "0123456789";
        String SPECIALS = "?><,\"';:\\/|][}{+=)(*&^%$#!";
        String ALL_CHARS = UPPERCASE + LOWERCASE + DIGITS + SPECIALS;
        SecureRandom random = new SecureRandom();
        List<Character> passwordChars = new ArrayList<>();


        passwordChars.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        passwordChars.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        passwordChars.add(DIGITS.charAt(random.nextInt(DIGITS.length())));
        passwordChars.add(SPECIALS.charAt(random.nextInt(SPECIALS.length())));

        for (int i = passwordChars.size(); i < length; i++) {
            passwordChars.add(ALL_CHARS.charAt(random.nextInt(ALL_CHARS.length())));
        }
        Collections.shuffle(passwordChars);
        StringBuilder ret = new StringBuilder();
        for(Character c : passwordChars) {
            ret.append(c);
        }
        return ret.toString();
    }

    public RegistrationMessage Register(String username, String password, String passwordConfirm, String nickName, String email, String gender) {
        App app = App.getInstance();
        if (isUsernameTaken(username)) {
            return new RegistrationMessage(null, "this username is already taken\nthis is a recommended username: " + username + "-9");
        }

        if (!isUsernameValid(username)) {
            return new RegistrationMessage(null, "Username format is invalid");
        }

        if (!isEmailValid(email)) {
            return new RegistrationMessage(null, "Email format is invalid");
        }

        if(!gender.equals("male") && !gender.equals("female")) {
            return new RegistrationMessage(null, "Gender is invalid (male / female)");
        }
        if (password.equals("random")) {
            String randomPassword = generateRandomPassword();
            String codedRandomPassword = sha256(randomPassword);
            User newUser = new User(username, codedRandomPassword, nickName, email, gender);
            app.addUser(newUser);
            return new RegistrationMessage(RegistrationCommand.askForPassword, "This is a random password: " + randomPassword + "\ndo you want to set this as your password?");
        }

        if (!isPasswordValid(password)) {
            return new RegistrationMessage(null, "Password format is invalid");
        }

        if (!doesPasswordHaveUpperCase(password)) {
            return new RegistrationMessage(null, "Password doesn't have uppercase letters'");
        }

        if (!doesPasswordHaveLowerCase(password)) {
            return new RegistrationMessage(null, "Password doesn't have lowercase letters'");
        }

        if (!doesPasswordHaveNumber(password)) {
            return new RegistrationMessage(null, "Password doesn't have digits");
        }

        if (!doesPasswordHaveSpecialChar(password)) {
            return new RegistrationMessage(null, "Password doesn't have special characters");
        }

        if (!isPasswordConfirmCorrect(password, passwordConfirm)) {
            return new RegistrationMessage(null, "Your Password confirmation is incorrect");
        }
        String codedPassword = sha256(password);
        User newUser = new User(username, codedPassword, nickName, email, gender);
        app.addUser(newUser);
        return new RegistrationMessage(RegistrationCommand.askQuestion, "You are successfully registered");

    }

    public RegistrationMessage acceptRandomPassword(boolean accept) {
        if (accept) {
            return new RegistrationMessage(null, "You are successfully registered");
        } else {
            App app = App.getInstance();
            app.removeLastUser();
            return new RegistrationMessage(RegistrationCommand.askForPassword, "Ok you can try again");
        }
    }

    public RegistrationMessage pickQuestion(int id, String answer, String answerConfirm) {
        App app = App.getInstance();
        if (!answer.equals(answerConfirm)) {
            return new RegistrationMessage(RegistrationCommand.askQuestion, "the answer and confirm are not equal");
        }
        Question question = Question.getQuestion(id);
        app.getLastUser().setQuestion(question);
        app.getLastUser().setAnswer(answer);
        return new RegistrationMessage(null, "Your question and answer successfully saved");
    }

    public RegistrationMessage login(String username, String password, boolean stayLoggedIn) {
        App app = App.getInstance();
        User user = app.getUserByUsername(username);
        if (!isUsernameTaken(username)) {
            return new RegistrationMessage(null, "Wrong Username");
        }
        String codedPassword = sha256(password);
        if (!user.getPassword().equals(codedPassword)) {
            return new RegistrationMessage(null, "Wrong Password");
        }
        if (stayLoggedIn) {
            app.setStayLoggedIn(true);
        } else {
            app.setStayLoggedIn(false);
        }

        app.setCurrentUser(user);
        app.setCurrentMenu(Menu.MainMenu);
        return new RegistrationMessage(null, "You logged in successfully! you are now in main menu");
    }

    public RegistrationMessage forgetPassword(String username) {
        if (!isUsernameTaken(username)) {
            return new RegistrationMessage(null, "Wrong Username");
        }
        App app = App.getInstance();
        return new RegistrationMessage(RegistrationCommand.answerQuestion, app.getUserByUsername(username).getQuestion().toString().substring(3));
    }

    public RegistrationMessage checkAnswer(String username, String answer) {
        App app = App.getInstance();
        User user = app.getUserByUsername(username);
        if (user.getAnswer().equals(answer)) {
            return new RegistrationMessage(null, user.getPassword());
        }
        return new RegistrationMessage(null, "Wrong Answer");
    }

    @Override
    public Message exit() {
        App.getInstance().saveApp();
        return null;
    }
}
