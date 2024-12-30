package org.example;

import org.example.utils.ConsoleHandler;
import org.example.utils.IHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConsoleHandlerTest {

  private IHandler consoleHandler;
  private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
  private final PrintStream printStream = new PrintStream(outputStream);
  private final InputStream originalSystemIn = System.in;
  private final String simulatedInput = "Test input\n";

  @BeforeEach
  void setUp() {
    System.setOut(printStream);
    System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));
    consoleHandler = new ConsoleHandler();
  }

  @Test
  void testWriteLine() {
    consoleHandler.writeLine("Hello, World!");
    assertEquals("Hello, World!", outputStream.toString().trim());
  }

  @Test
  void testReadLine() {
    String input = consoleHandler.readLine();
    assertEquals("Test input", input);
  }

  @AfterEach
  void restoreSystemInStream() {
    System.setIn(originalSystemIn);
  }

}

