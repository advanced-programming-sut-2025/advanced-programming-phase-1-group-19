package Views;

import Modules.App;

import java.util.Scanner;

public class AppView {

    private static AppView instance;
    private AppView() {}
    public static AppView getInstance() {
        if(instance == null) {
            instance = new AppView();
        }
        return instance;
    }

    private final Scanner scanner = new Scanner(System.in);

    private final App app = App.getInstance();

    public Scanner getScanner() {
        return scanner;
    }
    public void start(){
        while(true){
            switch (app.getCurrentMenu()) {
                case RegistrationMenu: {
                    RegistrationMenu menu = RegistrationMenu.getInstance();
                    menu.checkCommand();
                    break;
                }
                case MainMenu: {
                    MainMenu menu = MainMenu.getInstance();
                    menu.checkCommand();
                    break;
                }
                case ProfileMenu: {
                    ProfileMenu menu = ProfileMenu.getInstance();
                    menu.checkCommand();
                    break;
                }
                case GameMenu: {
                    GameMenu menu = GameMenu.getInstance();
                    menu.checkCommand();
                    break;
                }
                case null, default: {
                    System.out.println("CURRENT MENU == NULL!!!");
                    System.exit(0);
                }
            }
        }
    }
}
