package Controllers;

import Modules.App;

public interface UserInfoController {
    public default boolean isUsernameTaken(String username) {
        App app = App.getInstance();
        if(app.getUserByUsername(username) != null) {
            return true;
        }
        return false;
    }

    public default boolean isUsernameValid(String username) {
        String regex="^[a-zA-Z0-9-]+$";
        if(username.matches(regex)) {
            return true;
        }
        return false;
    }

    public default boolean isEmailValid(String email) {
        String regex="^\\s*(?!.*\\.\\.)[a-zA-Z0-9](?:[a-zA-Z0-9._-]*[a-zA-Z0-9])?@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?(?:\\.[a-zA-Z]{2,})+\\s*$";
        if(email.matches(regex)) {
            return true;
        }
        return false;
    }

    public default boolean isPasswordValid(String password) {
        String regex="^\\s*[a-zA-Z0-9!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?`~]+\\s*$";
        if(password.matches(regex)) {
            return true;
        }
        return false;
    }

    public default boolean doesPasswordHaveSpecialChar(String password) {
        String specialChars = "!@#$%^&*()_+-=[]{},.<>?/\\|\"':;`~";
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (specialChars.indexOf(ch)!=-1) {
                return true;
            }
        }
        return false;
    }

    public default boolean doesPasswordHaveUpperCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isUpperCase(ch)) {
                return true;
            }
        }
        return false;
    }

    public default boolean doesPasswordHaveLowerCase(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isLowerCase(ch)) {
                return true;
            }
        }
        return false;
    }

    public default boolean doesPasswordHaveNumber(String password) {
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);
            if (Character.isDigit(ch)) {
                return true;
            }
        }
        return false;
    }

    public default boolean isPasswordConfirmCorrect(String password, String passwordConfirm) {
        if(password.equals(passwordConfirm)) {
            return true;
        }
        return false;
    }
}
