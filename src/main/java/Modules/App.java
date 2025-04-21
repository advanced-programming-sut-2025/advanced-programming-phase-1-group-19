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
    private Menu currentMenu = Menu.RegistrationMenu;
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

    public Game getCurrentGame() {
        return currentGame;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public User getCurrentGameStarter() {
        return currentGameStarter;
    }

    public void setCurrentGameStarter(User currentGameStarter) {
        this.currentGameStarter = currentGameStarter;
    }

    public boolean isStayLoggedIn() {
        return stayLoggedIn;
    }

    public void setStayLoggedIn(boolean stayLoggedIn) {
        this.stayLoggedIn = stayLoggedIn;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Game> getGames() {
        return games;
    }

    public Game getCurrentGame() {
        return currentGame;
    }

    public User getCurrentGameStarter() {
        return currentGameStarter;
    }

    public void setCurrentGame(Game currentGame) {
        this.currentGame = currentGame;
    }

    public void setCurrentGameStarter(User currentGameStarter) {
        this.currentGameStarter = currentGameStarter;
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

    public void addGame(Game game) {
        games.add(game);
    }

    public void removeGame(Game game) {
        games.remove(game);
    }

    public void loadGame() {
//        TODO: load game from data based on loggedIn user and set currentGameStarter
    }

    public void saveGame() {}

}
