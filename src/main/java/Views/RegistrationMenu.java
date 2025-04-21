package Views;

import Controllers.Controller;
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
                String regex="^\\s*register -u (?<username>.+?) -p (?<password>.+?) (?<passwordConfirm>.+?) -n " +
                        "(?<nickname>.+?) -e (?<email>.+?) -g (?<gender>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                System.out.println(controller.Register(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4), matcher.group(5), matcher.group(6)).message());

                if(controller.Register(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4),
                        matcher.group(5), matcher.group(6)).command().equals(RegistrationCommand.askForPassword)){
                    if(scanner.nextLine().equals("Yes")){
                        System.out.println(controller.acceptRandomPassword(true).message());
                    }
                    else if(scanner.nextLine().equals("No")){
                        System.out.println(controller.acceptRandomPassword(false).message());
                    }
                }
                else if(controller.Register(matcher.group(1), matcher.group(2), matcher.group(3), matcher.group(4),
                        matcher.group(5), matcher.group(6)).command().equals(RegistrationCommand.askQuestion)){
                    for(int i=0;i<10;i++){
                        System.out.println(Question.values()[i]);
                    }
                    String answer = scanner.nextLine();
                    Pattern pattern1 = Pattern.compile("^\\s*pick question -q (?<questionnumber>.+?) -a (?<answer>.+?) -c (?<answerconfirm>.+?)\\s*$");
                    Matcher matcher1 = pattern1.matcher(answer);
                    if(matcher1.matches()){
                        System.out.println(controller.pickQuestion(Integer.parseInt(matcher1.group(1)),matcher1.group(2),matcher1.group(3)).message());
                    }
                }

                break;
            }
            case login: {
                String regex="^login\\s+-u\\s+(\\S+)\\s+-p\\s+(\\S+)(\\s+--stay-logged-in)?$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                Boolean stayLoggedIn = false;
                if(matcher.group(3)!=null){
//                    TODO: fix this
                    stayLoggedIn = true;
                }
                System.out.println(controller.login(matcher.group(1), matcher.group(2),stayLoggedIn ).message());
                break;
            }
            case forgetPassword: {
                String regex="^\\s*forget password -u (?<username>.+?)\\s*$";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(input);
                System.out.println(controller.forgetPassword(matcher.group(1)).message());
                if(controller.forgetPassword((matcher.group(1))).command().equals(RegistrationCommand.askQuestion)) {
                    String answer = scanner.nextLine();
                    String regex1 = "^\\s*answer -a (?<answer>.+?)\\s*$";
                    Pattern pattern1 = Pattern.compile(regex1);
                    Matcher matcher1 = pattern1.matcher(answer);
                    if (matcher1.matches()) {
                        System.out.println(controller.checkAnswer(matcher.group(1), matcher1.group(1)).message());
                    }
                }
                break;
            }
        }
    }


    @Override
    public void checkCommand() {
        String input = scanner.nextLine().trim();
        if (input.matches("^show current menu$")) {
            runCommand(RegistrationCommand.showCurrentMenu, input);
        }
        else if(input.matches("^\\s*register -u (?<username>.+?) -p (?<password>.+?) (?<passwordConfirm>.+?) -n " +
                "(?<nickname>.+?) -e (?<email>.+?) -g (?<gender>.+?)\\s*$")) {
            runCommand(RegistrationCommand.register, input);
        }
        else if(input.matches("^login\\s+-u\\s+(\\S+)\\s+-p\\s+(\\S+)(\\s+--stay-logged-in)?$")){
            runCommand(RegistrationCommand.login, input);
        }
        else if(input.matches("^\\s*forget password -u (?<username>.+?)\\s*$")){
            runCommand(RegistrationCommand.forgetPassword, input);
        }
    }
}
