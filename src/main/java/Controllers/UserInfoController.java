package Controllers;

public interface UserInfoController {
    public default boolean isUsernameTaken(String username) {}

    public default boolean isUsernameValid(String username) {}

    public default boolean isEmailValid(String email) {}

    public default boolean isPasswordValid(String password) {}

    public default boolean isPasswordWeak(String password) {}

    public default boolean isPasswordConfirmCorrect(String password, String passwordConfirm) {}
}
