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
        switch (command) {
            case startNewGame: {
                Pattern pattern = Pattern.compile("game new -u (?<usernames>.+?)");
                Matcher matcher = pattern.matcher(input);
                String allUsernames = matcher.group("usernames").trim();
                if(allUsernames.isEmpty()) {
                    System.out.println("You did not enter any usernames!");
                }
                String[] usernamesArray = allUsernames.split("\\s+");
                GameMessage result = gameController.startNewGame(usernamesArray);
                System.out.println(result.message());
                break;
            }
        }
//        TODO: for force terminate get all votes from users and send to controller
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
    }
}
