package Views;

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

    public Scanner getScanner() {
        return scanner;
    }
    public void start(){
        // TODO
        // Runs view for game
    }
}
