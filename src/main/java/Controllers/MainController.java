package Controllers;

import Modules.App;
import Modules.Enums.Menu;
import Modules.Interactions.Messages.MainMessage;
import Modules.Interactions.Messages.Message;

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

    @Override
    public MainMessage exit() {
        App app = App.getInstance();
        app.setCurrentMenu(Menu.RegistrationMenu);
        return new MainMessage(null, "You are now in registration menu");
    }
}
