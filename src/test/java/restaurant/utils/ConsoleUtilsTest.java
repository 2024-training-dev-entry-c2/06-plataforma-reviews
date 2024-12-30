package restaurant.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConsoleUtilsTest {

    private ConsoleUtils consoleUtils;
    private ByteArrayOutputStream outContent;

    @BeforeEach
    void setUp() {
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    }

    @Test
    @DisplayName("Test Read String")
    public void testGetString() {

        String input = "Test input";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        consoleUtils = new ConsoleUtils(new Scanner(System.in));

        String result = consoleUtils.getString("Enter input: ");
        assertEquals("Test input", result);
    }

    @Test
    @DisplayName("Test Read Integer with Valid Input")
    public void testGetInteger() {

        String input = "5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        consoleUtils = new ConsoleUtils(new Scanner(System.in));

        int result = consoleUtils.getInteger("Enter number: ");
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Test Read Integer with Invalid Input First")
    public void testGetIntegerWithInvalidInput() {
        String input = "abc\n5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        consoleUtils = new ConsoleUtils(new Scanner(System.in));

        int result = consoleUtils.getInteger("Enter number: ");
        assertEquals(5, result);
    }

    @Test
    @DisplayName("Test Read Double with Valid Input")
    public void testGetDouble() {
        String input = "5.0";
        System.setIn(new ByteArrayInputStream(input.getBytes()));
        consoleUtils = new ConsoleUtils(new Scanner(System.in));
        double result = consoleUtils.getDouble("Enter decimal number: ");
        assertEquals(5.0, result, 0.01);
    }

    @Test
    @DisplayName("Test Read Double with Invalid Input First")
    public void testGetDoubleWithInvalidInput() {
        String input = "abc\n5.5";
        System.setIn(new ByteArrayInputStream(input.getBytes()));


        consoleUtils = new ConsoleUtils(new Scanner(System.in));

        double result = consoleUtils.getDouble("Enter decimal number: ");
        assertEquals(5.5, result, 0.01);
    }
}
