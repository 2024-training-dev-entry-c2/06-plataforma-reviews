package org.example.utils;

import org.example.Interface.ICommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class ReviewInvokerTest {

  private ReviewInvoker reviewInvoker;
  private ICommand mockCommand;

  @BeforeEach
  void setUp() {
    reviewInvoker = new ReviewInvoker();
    mockCommand = mock(ICommand.class);
  }

  @Test
  @DisplayName("Test Execute Command")
  void testExecuteCommand() {
    reviewInvoker.setCommand(mockCommand);
    reviewInvoker.executeCommand();

    verify(mockCommand, times(1)).execute();
  }
}