package org.example.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleHandlerTest {

  private ConsoleHandler consoleHandler;
  private ByteArrayOutputStream outContent;

  @BeforeEach
  void setUp() {
    outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
  }

  @Test
  @DisplayName("Test Read Line")
  void testReadLine() {
    String input = "Test input";
    System.setIn(new ByteArrayInputStream(input.getBytes()));
    consoleHandler = new ConsoleHandler();
    assertEquals("Test input", consoleHandler.readLine());
  }

  @Test
  @DisplayName("Test Write Line")
  void testWriteLine() {
    consoleHandler = new ConsoleHandler();
    consoleHandler.writeLine("Test output");
    assertEquals("Test output\n", outContent.toString());
  }
}