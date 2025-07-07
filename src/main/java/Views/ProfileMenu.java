package Views;

import Controllers.ProfileController;
import Models.Interactions.Commands.ProfileCommand;
import Models.Interactions.Messages.ProfileMessage;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    private final AppView appView = AppView.getInstance();
    Scanner scanner = appView.getScanner();

    private void runCommand(ProfileCommand command, String input) {
        ProfileController controller = ProfileController.getInstance();
        switch (command) {
            case changeUsername: {
                String regex = "^\\s*change username -u (?<username>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(controller.changeUsername(matcher.group(1)).message());
                break;
            }
            case changeNickname: {
                String regex = "^\\s*change nickname -n (?<username>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(controller.changeNickname(matcher.group(1)).message());
                break;
            }
            case changeEmail: {
                String regex = "^\\s*change email -e (?<email>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(controller.changeEmail(matcher.group(1)).message());
                break;
            }
            case changePassword: {
                String regex = "^\\s*change password -p (?<newpassword>.+?) -o (?<oldpassword>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(controller.changePassword(matcher.group(1), matcher.group(2)).message());
                break;
            }
            case userInfo: {
                System.out.println(controller.showUserInfo().message());
                break;
            }
            case showCurrentMenu: {
                System.out.println(controller.showCurrentMenu().message());
                break;
            }
            case exit: {
                System.out.println(controller.exit().message());
                break;
            }
        }
    }


    @Override
    public void checkCommand() {

        String input = scanner.nextLine();
        if(input.matches("^\\s*change username -u (?<username>.+?)\\s*$")){
            runCommand(ProfileCommand.changeUsername, input);
        }
        else if(input.matches("^\\s*change nickname -n (?<nickname>.+?)\\s*$")){
            runCommand(ProfileCommand.changeNickname, input);
        }
        else if(input.matches("^\\s*change email -e (?<email>.+?)\\s*$")){
            runCommand(ProfileCommand.changeEmail, input);
        }
        else if(input.matches("^\\s*change password -p (?<newpassword>.+?) -o (?<oldpassword>.+?)\\s*$")){
            runCommand(ProfileCommand.changePassword, input);
        }
        else if(input.matches("^\\s*user info\\s*$")){
            runCommand(ProfileCommand.userInfo, input);
        }
        else if(input.matches("^\\s*show current menu\\s*$")){
            runCommand(ProfileCommand.showCurrentMenu, input);
        }
        else if(input.matches("^\\s*exit\\s*$")){
            runCommand(ProfileCommand.exit, input);
        }
        else {
            System.out.println("invalid command!");
        }
    }

    public void restartScanner() {
        scanner = AppView.getInstance().getScanner();
    }
}
