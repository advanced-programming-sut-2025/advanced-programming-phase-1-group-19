import Controllers.MainController;
import Controllers.RegistrationController;
import Modules.App;
import Views.AppView;
import Views.MainMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MainMenuTest {
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    private ByteArrayOutputStream outContent;

    private MainController controller = MainController.getInstance();

    @BeforeEach
    public void setUpStreams() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setIn(originalIn);
        App.getInstance().restart();
    }

    private void checkOutput(String input, String expected) {
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        AppView.getInstance().restartScanner();
        MainMenu.getInstance().restartScanner();

        MainMenu.getInstance().checkCommand();
        String output = outContent.toString().trim();
        assertTrue(output.startsWith(expected), "Failed on input: " + input + "\nExpected output: " + expected + "\nActual output: " + output);
    }

    @Test
    public void testInvalidCommand() {
        String[] inputs = {
                "klamdaskl \n",
                "register -usskq lala\n",
                "menuu Ennnter",
        };
        String expected = "invalid command!";

        for (int i = 0; i < inputs.length; i++) {
            checkOutput(inputs[i], expected);
        }
    }

    @Test
    public void testShowCurrentMenu() {
        String input = "show current menu\n";
        String expected = "main menu";
        checkOutput(input, expected);
    }

    @Test
    public void testMenuEnterInvalidMenu() {
        String input = "menu enter registration";
        String expected = "You can just go to profile or game menu";
        checkOutput(input, expected);
    }

    @Test
    public void testMenuEnterGameMenu() {
        String input = "menu enter game";
        String expected = "You are now in game menu";
        checkOutput(input, expected);
    }

    @Test
    public void testMenuEnterProfileMenu() {
        String input = "menu enter profile";
        String expected = "You are now in profile menu";
        checkOutput(input, expected);
    }

    @Test
    public void testUserLogout() {
        RegistrationController registrationController = RegistrationController.getInstance();
        registrationController.Register("test", "Test1!", "Test1!", "test", "test@test.com", "male");
        registrationController.login("test", "Test1!", false);
        String output = controller.logout().message();
        String expected = "You successfully logged out";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);
    }
}
