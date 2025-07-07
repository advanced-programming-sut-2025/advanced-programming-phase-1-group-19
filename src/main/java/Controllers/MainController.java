package Controllers;

import Models.App;
import Models.Enums.Menu;
import Models.Interactions.Messages.MainMessage;
import Models.Interactions.Messages.Message;
import com.sun.tools.javac.Main;

public class MainController extends Controller {
    private static MainController instance;
    private MainController() {}
    public static MainController getInstance() {
        if (instance == null) {
            instance = new MainController();
        }
        return instance;
    }

    @Override
    public MainMessage showCurrentMenu() {
        return new MainMessage(null,Menu.MainMenu.toString());
    }

    public MainMessage logout() {
        App app = App.getInstance();
        app.setCurrentUser(null);
        app.setCurrentMenu(Menu.RegistrationMenu);
        return new MainMessage(null,"You successfully logged out you are now in registration menu");
    }

    public MainMessage goToMenu(String menuName) {
        App app = App.getInstance();
        switch (menuName) {
            case "profile": {
                app.setCurrentMenu(Menu.ProfileMenu);
                return new MainMessage(null, "You are now in profile menu");
            }
            case "game": {
                app.setCurrentMenu(Menu.GameMenu);
                return new MainMessage(null, "You are now in game menu");
            }
        }
        return new MainMessage(null, "You can just go to profile or game menu");
    }

    public MainMessage exitApp() {
        App app = App.getInstance();
        if(!app.isStayLoggedIn()) {
            logout();
        }
        app.saveApp();
        return new MainMessage(null, "You Exited!");
    }

    @Override
    public MainMessage exit() {
        App app = App.getInstance();
        app.setCurrentUser(null);
        app.setCurrentMenu(Menu.RegistrationMenu);
        return new MainMessage(null, "You are now in registration menu");
    }
}
