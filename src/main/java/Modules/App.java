package Modules;

import Modules.Enums.Menu;

import java.util.ArrayList;

public class App {

    private static App instance;
    private App() {}
    public static App getInstance() {
        if (instance == null) {
            instance = new App();
        }
        return instance;
    }

    private final ArrayList<User> users = new ArrayList<>();
    private final ArrayList<Game> games = new ArrayList<>();
    private Menu currentMenu;
    private User currentUser;
    private Game currentGame;
    private User currentGameStarter;
    private boolean stayLoggedIn;

    public Menu getCurrentMenu() {
        return currentMenu;
    }

    public void setCurrentMenu(Menu currentMenu) {
        this.currentMenu = currentMenu;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public User getUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public void addGame(Game game) {}

    public void removeGame(Game game) {}

    public void loadGame() {
//        TODO: load game from data based on loggedIn user and set currentGameStarter
    }

    public void saveGame() {}

}
