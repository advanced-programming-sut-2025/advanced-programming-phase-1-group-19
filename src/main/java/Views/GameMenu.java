package Views;

import Controllers.*;
import Modules.Interactions.Commands.*;
import Modules.Interactions.Messages.*;

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
            case showEnergy: {
                System.out.println(gameController.showEnergy().message());
                break;
            }
            case cheatEnergy: {
                String pattern="^\\s*energy set -v (?<value>.+?)\\s*$";
                Pattern pattern1 = Pattern.compile(pattern);
                Matcher matcher1 = pattern1.matcher(input);
                System.out.println(gameController.cheatEnergySet(Integer.parseInt(matcher1.group(1))).message());
                break;
            }
            case energyUnlimited: {
                System.out.println(gameController.unlimitedEnergySet().message());
                break;
            }
            case inventoryShow:{
                System.out.println(gameController.showInventory().message());
                break;
            }
            case inventoryTrash: {
                String pattern="^\\s*inventory trash -i (?<itemName>.+?)(?: -n (?<number>\\d+))?\\s*$";
                Pattern pattern1 = Pattern.compile(pattern);
                Matcher matcher1 = pattern1.matcher(input);
                if(input.contains("-n")) {
                    System.out.println(gameController.inventoryTrash(matcher1.group(1),Integer.parseInt(matcher1.group(2)) ,false));
                }
                else {
                    System.out.println(gameController.inventoryTrash(matcher1.group(1), 0,true));

                }
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
        else if(input.matches("^\\s*exit game\\s*$")){
            runCommand(GameCommand.exitGame, "");
        }
        else if(input.matches("^\\s*energy show\\s*$")){
            runCommand(GameCommand.showEnergy,"");
        }
        else if(input.matches("^\\s*energy set -v (?<value>.+?)\\s*$")){
            runCommand(GameCommand.cheatEnergy,input);
        }
        else if(input.matches("^\\s*energy unlimited\\s*$")){
            runCommand(GameCommand.energyUnlimited,"");
        }
        else if(input.matches("^\\s*inventory trash -i (?<itemName>.+?) -n (?<number>.+?)\\s*$")){
            runCommand(GameCommand.inventoryTrash,input);
        }
    }
}
