package Views;

import Controllers.*;
import Modules.Interactions.Commands.*;
import Modules.Interactions.Messages.*;
import Modules.Map.Position;

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
        switch (command) {
            case startNewGame: {
                Pattern pattern = Pattern.compile("game new -u (?<usernames>.+?)");
                Matcher matcher = pattern.matcher(input);
                String allUsernames = matcher.group("usernames").trim();
                if (allUsernames.isEmpty()) {
                    System.out.println("You did not enter any usernames!");
                }
                String[] usernamesArray = allUsernames.split("\\s+");
                GameMessage result = gameController.startNewGame(usernamesArray);
                System.out.println(result.message());
                break;
            }
            case showTime: {
                System.out.println(gameController.showTime().message());
                break;
            }
            case showDate: {
                System.out.println(gameController.showDate().message());
                break;
            }
            case showDateTime: {
                System.out.println(gameController.showDateTime().message());
                break;
            }
            case showDayOfWeek: {
                System.out.println(gameController.showDayOfWeak().message());
                break;
            }
            case cheatAdvanceTime: {
                Pattern pattern = Pattern.compile("^\\s*cheat advance time (?<X>\\d+)h\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.cheatAdvanceTime(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case cheatAdvanceDate: {
                Pattern pattern = Pattern.compile("^\\s*cheat advance date (?<X>\\d+)d\\s*$");
                Matcher matcher = pattern.matcher(input);
                System.out.println(gameController.cheatAdvanceDate(Integer.parseInt(matcher.group("X"))).message());
                break;
            }
            case season: {
                System.out.println(gameController.showSeason().message());
                break;
            }
            case exitGame: {
                System.out.println(gameController.exitGame().message());
                break;
            }
            case nextTurn: {
                System.out.println(gameController.nextTurn().message());
                break;
            }
            case forceTerminate: {
                System.out.println(gameController.forceTerminate().message());
                break;
            }
            case walk: {
                Pattern pattern = Pattern.compile("^walk -l (?<x>\\d+) (?<y>\\d+)");
                Matcher matcher = pattern.matcher(input);
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                System.out.println(gameController.walk(new Position(x, y)).message());
                break;
            }
            case printMap: {
                Pattern pattern = Pattern.compile("^print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)$");
                Matcher matcher = pattern.matcher(input);
                int x = Integer.parseInt(matcher.group("x"));
                int y = Integer.parseInt(matcher.group("y"));
                int size = Integer.parseInt(matcher.group("size"));
                System.out.println(gameController.printMap(new Position(x, y), size).message());
//                TODO: colorize for optional points
                break;
            }
            case helpPrintMap: {
                System.out.println(gameController.helpPrintMap().message());
                break;
            }
        }
    }


    private final AppView appView = AppView.getInstance();
    private final Scanner scanner = appView.getScanner();
    private final GameController gameController = GameController.getInstance();

    @Override
    public void checkCommand() {
        String input = scanner.nextLine().trim();
        if(input.matches("game new -u (?<usernames>.+?)")) {
            runCommand(GameCommand.startNewGame, input);
        }
        else if(input.matches("^\\s*Time\\s*$")){
            runCommand(GameCommand.showTime, "");
        }
        else if(input.matches("^\\s*Date\\s*$")){
            runCommand(GameCommand.showDate, "");
        }
        else if(input.matches("^\\s*DateTime\\s*$")){
            runCommand(GameCommand.showDateTime, "");
        }
        else if(input.matches("^\\s*day of the week\\s*$")){
            runCommand(GameCommand.showDayOfWeek, "");
        }
        else if(input.matches("^\\s*cheat advance time (?<X>\\d+)h\\s*$")){
            runCommand(GameCommand.cheatAdvanceTime, input);
        }
        else if(input.matches("^\\s*cheat advance date (?<X>\\d+)d\\s*$")){
            runCommand(GameCommand.cheatAdvanceDate, input);
        }
        else if(input.matches("^\\s*season\\s*$")){
            runCommand(GameCommand.season, "");
        }
        else if(input.matches("^\\s*next turn\\s*$")){
            runCommand(GameCommand.nextTurn, "");
        }
        else if(input.matches("^\\s*force terminate\\s*$")){
            runCommand(GameCommand.forceTerminate, "");
        }
        else if(input.matches("^walk -l (?<x>\\d+) (?<y>\\d+)$")) {
            runCommand(GameCommand.walk, input);
        }
        else if(input.matches("^print map -l (?<x>\\d+) (?<y>\\d+) -s (?<size>\\d+)$")) {
            runCommand(GameCommand.printMap, input);
        }
        else if(input.matches("^help reading map$")) {
            runCommand(GameCommand.helpPrintMap, input);
        }
        else if(input.matches("^\\s*exit game\\s*$")){
            runCommand(GameCommand.exitGame, "");
        }
    }
}
