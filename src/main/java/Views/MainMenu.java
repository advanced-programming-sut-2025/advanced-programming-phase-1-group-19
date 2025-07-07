package Views;

import Controllers.MainController;
import Models.Interactions.Commands.MainCommand;
import Models.Interactions.Messages.MainMessage;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenu implements AppMenu {
    private static MainMenu instance;

    private MainMenu() {
    }

    public static MainMenu getInstance() {
        if (instance == null) {
            instance = new MainMenu();
        }
        return instance;
    }

    private final AppView appView = AppView.getInstance();

    Scanner scanner = appView.getScanner();

    private void runCommand(MainCommand command, String input) {
        MainController controller=MainController.getInstance();
        switch (command) {
            case enter:{
                String regex="^\\s*menu enter (?<menuname>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher1 = pattern.matcher(input);
                if(!matcher1.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(controller.goToMenu(matcher1.group(1)).message());
                break;
            }
            case showCurrentMenu:{
                System.out.println(controller.showCurrentMenu().message());
                break;
            }
            case logout:{
                System.out.println(controller.logout().message());
                break;
            }
            case exitApp: {
                controller.exitApp();
                System.out.println("you have exited the app!");
                System.exit(0);
                break;
            }
            case exit:{
                System.out.println(controller.exitApp().message());
                break;
            }
        }

    }



    @Override
    public void checkCommand() {
        String input = scanner.nextLine();
        if(input.matches("^\\s*menu enter (?<menuname>.+?)\\s*$")){
            runCommand(MainCommand.enter, input);
        }
        else if(input.matches("^\\s*show current menu$")){
            runCommand(MainCommand.showCurrentMenu, input);
        }
        else if(input.matches("^\\s*user logout\\s*$")){
            runCommand(MainCommand.logout, input);
        }
        else if(input.matches("^exit app$")) {
            runCommand(MainCommand.exitApp, input);
        }
        else if(input.matches("^\\s*exit\\s*$")){
            runCommand(MainCommand.exit, input);
        }
        else {
            System.out.println("invalid command!");
        }
    }

    public void restartScanner() {
        scanner = AppView.getInstance().getScanner();
    }

}
