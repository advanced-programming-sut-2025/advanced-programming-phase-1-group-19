import Controllers.MainController;
import Controllers.ProfileController;
import Modules.App;
import Views.AppView;
import Views.MainMenu;
import Views.ProfileMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProfileMenuTest {
    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    private ByteArrayOutputStream outContent;

    private ProfileController controller = ProfileController.getInstance();

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

        ProfileMenu.getInstance().checkCommand();
        String output = outContent.toString().trim();
        assertTrue(output.startsWith(expected), "Failed on input: " + input + "\nExpected output: " + expected + "\nActual output: " + output);
    }

    @Test
    public void testInvalidCommand() {
        String[] inputs = {
                "klamdaskl \n",
                "register -usskq lala\n",
                "menuu Ennnter",
                "change namme",
                "login -u test -p Test1!"
        };
        String expected = "invalid command!";

        for (int i = 0; i < inputs.length; i++) {
            checkOutput(inputs[i], expected);
        }
    }

    @Test
    public void testShowCurrentMenu() {
        String input = "show current menu\n";
        String expected = "profile menu";
        checkOutput(input, expected);
    }
}
