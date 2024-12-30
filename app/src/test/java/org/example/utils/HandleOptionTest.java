package org.example.utils;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.constants.MenuOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class HandleOptionTest {

  private HandleOption handleOption;
  private IConsoleHandler mockConsoleHandler;
  private ReviewFactory mockReviewFactory;

  @BeforeEach
  void setup() {
    mockConsoleHandler = mock(IConsoleHandler.class);
    mockReviewFactory = mock(ReviewFactory.class);
    handleOption = new HandleOption(mockConsoleHandler);
    handleOption.reviewFactory = mockReviewFactory;
  }

  @Test
  @DisplayName("Test Execute Add Review to Dish Option")
  void testExecuteAddReviewToDishOption() {
    when(mockConsoleHandler.readLine()).thenReturn("Reviewer 1", "5.0", "Excellent", "Dish 1");
    doNothing().when(mockReviewFactory).createReview(eq("Dish"), anyString(), anyDouble(), anyString(), any());

    handleOption.execute(MenuOption.ADD_REVIEW_TO_DISH.getOptionNumber());

    verify(mockReviewFactory).createReview(eq("Dish"), anyString(), anyDouble(), anyString(), any());
  }

  @Test
  @DisplayName("Test Execute Add Review to Restaurant Option")
  void testExecuteAddReviewToRestaurantOption() {
    when(mockConsoleHandler.readLine()).thenReturn("Reviewer 1", "5.0", "Excellent", "Restaurant 1");
    doNothing().when(mockReviewFactory).createReview(eq("Restaurant"), anyString(), anyDouble(), anyString(), any());

    handleOption.execute(MenuOption.ADD_REVIEW_TO_RESTAURANT.getOptionNumber());

    verify(mockReviewFactory).createReview(eq("Restaurant"), anyString(), anyDouble(), anyString(), any());
  }

//  @Test
//  @DisplayName("Test Execute Exit Option")
//  void testExecuteExitOption() {
//    doNothing().when(mockConsoleHandler).writeLine("Saliendo...");
//
//    handleOption.execute(MenuOption.EXIT.getOptionNumber());
//
//    verify(mockConsoleHandler).writeLine("Saliendo...");
//  }

  @Test
  @DisplayName("Test Execute Invalid Option")
  void testExecuteInvalidOption() {
    handleOption.execute(999);

    verify(mockConsoleHandler).writeLine("Opcion no encontrada");
  }

  @Test
  @DisplayName("Test Execute Valid Command Option")
  void testExecuteValidCommandOption() {
    ICommand mockCommand = mock(ICommand.class);
    AppMenu.commandMap.put(MenuOption.CREATE_DISH, mockCommand);

    handleOption.execute(MenuOption.CREATE_DISH.getOptionNumber());

    verify(mockCommand).execute();
  }
}