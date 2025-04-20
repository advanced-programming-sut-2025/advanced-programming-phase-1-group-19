package Views;

import Controllers.MainController;
import Modules.Interactions.Commands.MainCommand;
import Modules.Interactions.Messages.MainMessage;

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
                System.out.println(controller.goToMenu(matcher1.group(1)));
                break;
            }
            case showCurrentMenu:{
                controller.showCurrentMenu();
                //TODO:check showCurrent menu function in controller abstract class
                break;
            }
            case logout:{
                System.out.println(controller.logout().message());
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
    }

}
