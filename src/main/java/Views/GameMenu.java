package Views;

import Controllers.GameController;
import Modules.Interactions.Commands.GameCommand;
import Modules.Interactions.Messages.GameMessage;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameMenu implements AppMenu {
    private static GameMenu instance;

    private GameMenu() {
    }

    public static GameMenu getInstance() {
        if (instance == null) {
            instance = new GameMenu();
        }
        return instance;
    }

    public String getSingleLine(String output) {
        if(!output.isEmpty()) {
            System.out.println(output);
        }
        return scanner.nextLine().trim();
    }
    private void runCommand(GameCommand command, String input) {
//        TODO: for force terminate get all votes from users and send to controller\
        switch (command){
            case ShowTime :{
                System.out.println(controller.showTime().message());
                break;
            }
            case ShowDate :{
                System.out.println(controller.showDate().message());
                break;
            }
            case ShowDateTime :{
                System.out.println(controller.showDateTime().message());
                break;
            }
            case ShowDayOfWeek:{
                System.out.println(controller.showDayOfWeak().message());
                break;
            }
            case CheatAdvanceTime:{
                Pattern pattern = Pattern.compile("^\\s*cheat advance time (?<X>\\d+)h\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(controller.cheatAdvanceTime(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case CheatAdvanceDate:{
                Pattern pattern = Pattern.compile("^\\s*cheat advance date (?<X>\\d+)d\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(controller.cheatAdvanceDate(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case Season:{
                System.out.println(controller.showSeason().message());
                break;
            }
            case ExitGame:{
                System.out.println(controller.exitGame().message());
            }
            case NextTurn:{
                System.out.println(controller.nextTurn().message());
            }
            case ForceTerminate:{
                System.out.println(controller.forceTerminate().message());
            }
        }
    }

    private final AppView appView = AppView.getInstance();
    private final Scanner scanner = appView.getScanner();
    private final GameController controller = GameController.getInstance();
    @Override
    public void checkCommand() {
        String input = scanner.nextLine().trim();
        if(input.matches("^\\s*Time\\s*$")){
            runCommand(GameCommand.ShowTime, "");
        }
        else if(input.matches("^\\s*Date\\s*$")){
            runCommand(GameCommand.ShowDate, "");
        }
        else if(input.matches("^\\s*DateTime\\s*$")){
            runCommand(GameCommand.ShowDateTime, "");
        }
        else if(input.matches("^\\s*day of the week\\s*$")){
            runCommand(GameCommand.ShowDayOfWeek, "");
        }
        else if(input.matches("^\\s*cheat advance time (?<X>\\d+)h\\s*$")){
            runCommand(GameCommand.CheatAdvanceTime, input);
        }
        else if(input.matches("^\\s*cheat advance date (?<X>\\d+)d\\s*$")){
            runCommand(GameCommand.CheatAdvanceDate, input);
        }
        else if(input.matches("^\\s*season\\s*$")){
            runCommand(GameCommand.Season, "");
        }
        else if(input.matches("^\\s*next turn\\s*$")){
            runCommand(GameCommand.NextTurn, "");
        }
        else if(input.matches("^\\s*force terminate\\s*$")){
            runCommand(GameCommand.ForceTerminate, "");
        }
        else if(input.matches("^\\s*exit game\\s*$")){
            runCommand(GameCommand.ExitGame, "");
        }
    }
}
