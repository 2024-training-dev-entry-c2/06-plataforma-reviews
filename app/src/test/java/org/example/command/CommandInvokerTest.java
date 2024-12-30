package org.example.command;

import org.example.interfaces.ICommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CommandInvokerTest {
  private ICommand mockCommand1;
  private ICommand mockCommand2;
  private CommandInvoker commandInvoker;

  @BeforeEach
  void setUp() {
    mockCommand1 = mock(ICommand.class);
    mockCommand2 = mock(ICommand.class);
    commandInvoker = new CommandInvoker();
  }

  @Test
  void testRegisterCommand() {
    commandInvoker.registerCommand(1, "Descripción comando 1", mockCommand1);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    commandInvoker.executeCommand(1);

    verify(mockCommand1).execute();

    System.setOut(System.out);
  }

  @Test
  void testExecuteInvalidCommand() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    commandInvoker.executeCommand(99);

    String output = outContent.toString();
    assertTrue(output.contains("Opción no válida."));

    System.setOut(System.out);
  }

  @Test
  void testPrintMenu() {
    commandInvoker.registerCommand(1, "Descripción comando 1", mockCommand1);
    commandInvoker.registerCommand(2, "Descripción comando 2", mockCommand2);

    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));

    commandInvoker.printMenu();

    String output = outContent.toString();
    assertTrue(output.contains("1. Descripción comando 1"));
    assertTrue(output.contains("2. Descripción comando 2"));

    System.setOut(System.out);
  }

  @Test
  void testMultipleCommandsExecution() {
    commandInvoker.registerCommand(1, "Descripción comando 1", mockCommand1);
    commandInvoker.registerCommand(2, "Descripción comando 2", mockCommand2);

    commandInvoker.executeCommand(1);
    verify(mockCommand1).execute();

    commandInvoker.executeCommand(2);
    verify(mockCommand2).execute();
  }
}