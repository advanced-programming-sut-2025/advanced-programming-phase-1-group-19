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

public class RegistrationController extends Controller implements UserInfoController{
    private static RegistrationController instance;
    private RegistrationController() {}
    public static RegistrationController getInstance() {
        if (instance == null) {
            instance = new RegistrationController();
        }
        return instance;
    }

    @Override
    public RegistrationMessage showCurrentMenu(){
        return new RegistrationMessage(null,Menu.RegistrationMenu.toString());
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
        return passwordChars.toString();
    }

    public RegistrationMessage Register (String username, String password, String passwordConfirm, String nickName, String email, String gender) {
        App app = App.getInstance();
        if(isUsernameTaken(username)){
            return new RegistrationMessage(null, "this username is already taken\nthis is a recommended password: "+password+"-9");
        }

        if(!isUsernameValid(username)){
            return new RegistrationMessage(null,"Username format is invalid");
        }

        if(!isEmailValid(email)){
            return new RegistrationMessage(null,"Email format is invalid");
        }
        if(password.equals("random")){
            String randomPassword = generateRandomPassword();
            User newUser=new User(username,randomPassword,nickName,email,gender);
            app.addUser(newUser);
            return new RegistrationMessage(RegistrationCommand.askForPassword, "This is a random password: "+password+"\ndo you want to set this as your password?");
        }

        if(!isPasswordValid(password)){
            return new RegistrationMessage(null,"Password format is invalid");
        }

        if(!doesPasswordHaveUpperCase(password)){
            return new RegistrationMessage(null,"Password doesn't have uppercase letters'");
        }

        if(!doesPasswordHaveLowerCase(password)){
            return new RegistrationMessage(null,"Password doesn't have lowercase letters'");
        }

        if(!doesPasswordHaveNumber(password)){
            return new RegistrationMessage(null,"Password doesn't have digits");
        }

        if(!doesPasswordHaveSpecialChar(password)){
            return new RegistrationMessage(null,"Password doesn't have special characters");
        }

        if(!isPasswordConfirmCorrect(password, passwordConfirm)){
            return new RegistrationMessage(null,"Your Password confirmation is incorrect");
        }
        User newUser=new User(username,password,nickName,email,gender);
        app.addUser(newUser);
        return new RegistrationMessage(RegistrationCommand.askQuestion, "You are successfully registered");

    }

    public RegistrationMessage acceptRandomPassword(boolean accept) {
        if(accept){
            return new RegistrationMessage(null, "You are successfully registered");
        }
        else{
            App app = App.getInstance();
            app.removeLastUser();
            return new RegistrationMessage(RegistrationCommand.askForPassword, "Ok you can try again");
        }
    }

    public RegistrationMessage pickQuestion(int id, String answer, String answerConfirm) {
        App app = App.getInstance();
        if (!answer.equals(answerConfirm)) {
            return new RegistrationMessage(null, "the answer and confirm are not equal");
        }
        switch (id) {
            case 1: {
                app.getLastUser().setQuestion(Question.first);
                break;
            }
            case 2: {
                app.getLastUser().setQuestion(Question.second);
                break;
            }
            case 3: {
                app.getLastUser().setQuestion(Question.third);
                break;
            }
            case 4: {
                app.getLastUser().setQuestion(Question.fourth);
                break;
            }
            case 5: {
                app.getLastUser().setQuestion(Question.fifth);
                break;
            }
            case 6: {
                app.getLastUser().setQuestion(Question.sixth);
                break;
            }
            case 7: {
                app.getLastUser().setQuestion(Question.seventh);
                break;
            }
            case 8: {
                app.getLastUser().setQuestion(Question.eighth);
                break;
            }
            case 9: {
                app.getLastUser().setQuestion(Question.ninth);
                break;
            }
            case 10: {
                app.getLastUser().setQuestion(Question.tenth);
                break;
            }
        }
        app.getLastUser().setAnswer(answer);
        return new RegistrationMessage(null, "Your question and answer successfully saved");
    }

    public RegistrationMessage login(String username, String password, boolean stayLoggedIn) {
        App app = App.getInstance();
        User user = app.getUserByUsername(username);
        if (!isUsernameTaken(username)) {
            return new RegistrationMessage(null, "Wrong Username");
        }
        if (!user.getPassword().equals(password)) {
            return new RegistrationMessage(null, "Wrong Password");
        }
        if (stayLoggedIn) {
            app.setStayLoggedIn(true);
        } else {
            app.setStayLoggedIn(false);
        }

        app.setCurrentUser(user);
        return new RegistrationMessage(null, "You logged in successfully");
    }

    public RegistrationMessage forgetPassword(String username) {
        if (!isUsernameTaken(username)) {
            return new RegistrationMessage(null, "Wrong Username");
        }
        App app = App.getInstance();
        return new RegistrationMessage(RegistrationCommand.answerQuestion, app.getUserByUsername(username).getQuestion().toString());
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
//        TODO: exit app
    }
}
