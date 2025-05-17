package Views;

import Controllers.RegistrationController;
import Modules.Enums.Question;
import Modules.Interactions.Commands.RegistrationCommand;
import Modules.Interactions.Messages.RegistrationMessage;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationMenu implements AppMenu {
    private static RegistrationMenu instance;

    private RegistrationMenu() {
    }

    public static RegistrationMenu getInstance() {
        if (instance == null) {
            instance = new RegistrationMenu();
        }
        return instance;
    }

    private final AppView appView = AppView.getInstance();
    Scanner scanner = appView.getScanner();

    private void runCommand(RegistrationCommand command, String input) {
        RegistrationController controller = RegistrationController.getInstance();
        switch (command) {
            case showCurrentMenu: {
                System.out.println(controller.showCurrentMenu().message());
                break;
            }
            case register: {
                String regex = "^register -u (?<username>.+?) -p (?<password>.+?) -pc (?<passwordConfirm>.+?) -n (?<nickname>.+?) -e (?<email>.+?) -g (?<gender>.+?)$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if (!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                RegistrationMessage message = controller.Register(matcher.group("username"), matcher.group("password"), matcher.group("passwordConfirm"), matcher.group("nickname"), matcher.group("email"), matcher.group("gender"));
                System.out.println(message.message());

                if (message.command() != null) {

                    if (message.command().equals(RegistrationCommand.askForPassword)) {
                        String nextLine = scanner.nextLine();
                        if (nextLine.trim().equalsIgnoreCase("yes")) {
                            System.out.println(controller.acceptRandomPassword(true).message());
                        } else if (nextLine.trim().equalsIgnoreCase("no")) {
                            System.out.println(controller.acceptRandomPassword(false).message());
                        }
                    } else if (message.command().equals(RegistrationCommand.askQuestion)) {
                        System.out.println("now pick a question and answer it:");
                        for (Question question : Question.values()) {
                            System.out.println(question);
                        }
                        String answer = scanner.nextLine().trim();
                        Pattern pattern1 = Pattern.compile("pick question -q (?<questionnumber>\\d) -a (?<answer>.+?) -c (?<answerconfirm>.+?)$");
                        Matcher matcher1 = pattern1.matcher(answer);
                        RegistrationMessage result = new RegistrationMessage(RegistrationCommand.askQuestion, "");
                        if (matcher1.matches()) {
                            result = controller.pickQuestion(Integer.parseInt(matcher1.group(1)), matcher1.group(2), matcher1.group(3));
                        }
                        while (!matcher1.matches() || result.command() != null) {
                            if (!result.message().isEmpty()) {
                                System.out.println(result.message());
                            }
                            System.out.println("use this format: pick question -q <question_number> -a <answer> -c <answer_confirm>");
                            answer = scanner.nextLine().trim();
                            matcher1 = pattern1.matcher(answer);
                            if (matcher1.matches()) {
                                result = controller.pickQuestion(Integer.parseInt(matcher1.group(1)), matcher1.group(2), matcher1.group(3));
                            } else {
                                result = new RegistrationMessage(RegistrationCommand.askQuestion, "");
                            }
                        }
                        System.out.println(result.message());

                    }
                }

                break;
            }
            case login: {
                String regex = "^login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)(\\s+--stay-logged-in)?$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                Boolean stayLoggedIn = false;
                if (input.contains("--stay-logged-in")) {
//                    TODO: fix this
                    stayLoggedIn = true;
                }
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                System.out.println(controller.login(matcher.group(1), matcher.group(2), stayLoggedIn).message());
                break;
            }
            case forgetPassword: {
                String regex = "^\\s*forget password -u (?<username>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                if(!matcher.matches()) {
                    System.out.println("invalid command!");
                }
                RegistrationMessage message = controller.forgetPassword(matcher.group(1));
                System.out.println(message.message());
                if (message.command() != null && message.command().equals(RegistrationCommand.answerQuestion)) {
                    String answer = scanner.nextLine();
                    String regex1 = "^\\s*answer -a (?<answer>.+?)\\s*$";
                    Pattern pattern1 = Pattern.compile(regex1);
                    Matcher matcher1 = pattern1.matcher(answer);
                    if(!matcher1.matches()) {
                        System.out.println("wrong format, use \"answer -a <answer>\"");
                    }
                    if (matcher1.matches()) {
                        System.out.println(controller.checkAnswer(matcher.group(1), matcher1.group(1)).message());
                    }
                }
                break;
            }
            case exit: {
                controller.exit();
                System.out.println("you have exited the app!");
                System.exit(0);
                break;
            }
        }
    }


    @Override
    public void checkCommand() {
        String input = scanner.nextLine().trim();
        if (input.matches("^show current menu$")) {
            runCommand(RegistrationCommand.showCurrentMenu, input);
        } else if (input.matches("^register -u (?<username>.+?) -p (?<password>.+?) -pc (?<passwordConfirm>.+?) -n (?<nickname>.+?) -e (?<email>.+?) -g (?<gender>.+?)$")) {
            runCommand(RegistrationCommand.register, input);
        } else if (input.matches("^login\\s+-u\\s+(\\S+)\\s+-p\\s+(\\S+)(\\s+--stay-logged-in)?$")) {
            runCommand(RegistrationCommand.login, input);
        } else if (input.matches("^\\s*forget password -u (?<username>.+?)\\s*$")) {
            runCommand(RegistrationCommand.forgetPassword, input);
        }
        else if (input.matches("^exit$")) {
            runCommand(RegistrationCommand.exit, input);
        }
        else {
            System.out.println("invalid command!");
        }
    }

    public void restartScanner() {
        scanner = AppView.getInstance().getScanner();
    }

}
