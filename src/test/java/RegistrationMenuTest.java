import Controllers.RegistrationController;
import Modules.App;
import Views.AppView;
import Views.RegistrationMenu;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegistrationMenuTest {

    private final PrintStream originalOut = System.out;
    private final java.io.InputStream originalIn = System.in;

    private ByteArrayOutputStream outContent;

    private RegistrationController controller = RegistrationController.getInstance();

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
        RegistrationMenu.getInstance().restartScanner();

        RegistrationMenu.getInstance().checkCommand();
        String output = outContent.toString().trim();
        assertTrue(output.startsWith(expected), "Failed on input: " + input + "\nExpected output: " + expected + "\nActual output: " + output);
    }

    @Test
    public void testInvalidCommand() {
        String[] inputs = {
                "klamdaskl \n",
                "register -usskq lala\n",
                "register -u alai -p ksks\n",
                "register -u test -p Test1! Test1! -n test -e test@gmail.com -g male\n",
        };
        String[] expecteds = {
                "invalid command!",
                "invalid command!",
                "invalid command!",
                "invalid command!",
        };

        for (int i = 0; i < inputs.length; i++) {
            checkOutput(inputs[i], expecteds[i]);
        }
    }

    @Test
    public void testShowCurrentMenu() {
        String input = "show current menu\n";
        String expected = "registration menu";
        checkOutput(input, expected);
    }

    @Test
    public void testRegisterValidInput() {
        String output = controller.Register("test", "Test1!", "Test1!", "test", "test@test.com", "male").message();
        String expected = "You are successfully registered";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test-ALI12", "Test1!", "Test1!", "test", "test@test.com", "male").message();
        expected = "You are successfully registered";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);
    }

    @Test
    public void testRegisterRepeatedUsername(){
        controller.Register("test", "Test1!", "Test1!", "test", "test@test.com", "male");
        String output = controller.Register("test", "Test123!", "Test123!", "nope", "tada@gamil.com", "female").message();
        String expected = "this username is already taken";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);
    }

    @Test
    public void testRegisterInvalidUsername(){
        controller.Register("test!_hsh", "Test1!", "Test1!", "test", "test@test.com", "male");
        String output = controller.Register("test!_hsh", "Test1!", "Test1!", "test", "test@test.com", "male").message();
        String expected = "Username format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);
    }

    @Test
    public void testRegisterInvalidEmail() {
        String output = controller.Register("test", "Test1!", "Test1!", "test", "test@t@est.com", "male").message();
        String expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "testtest.com", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "tetetst_!mms@gmail.com", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "!@#as!mms@gmail.com", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "-ldldl-@gmail.com", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "ldld..lsA@gmail.com", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "test@gmailcom", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "test@gmailcom.s", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "test@gmailc_om.r!s", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

        output = controller.Register("test", "Test1!", "Test1!", "test", "test@-gmailcom.rs-", "male").message();
        expected = "Email format is invalid";
        assertTrue(output.startsWith(expected), "Expected output: " + expected + "\nActual output: " + output);

    }



}
