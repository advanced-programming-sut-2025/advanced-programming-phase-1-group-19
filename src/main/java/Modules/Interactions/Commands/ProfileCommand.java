package Models.Interactions.Commands;

public enum ProfileCommand implements Command {
    changeUsername ,
    changeNickname ,
    changeEmail,
    changePassword,
    userInfo,
    exit,
    showCurrentMenu;
}
