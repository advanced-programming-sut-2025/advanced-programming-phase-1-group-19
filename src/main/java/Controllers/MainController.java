package Controllers;

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

    public MainMessage logout() {

    }

    public MainMessage goToMenu(String menuName) {
//        TODO: go to profile and game
    }

    @Override
    public Message exit() {
//        TODO: go to registration
    }
}
