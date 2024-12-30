package org.example.controllers;

import org.example.command.CommandInvoker;
import org.example.interfaces.IHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ReviewMenuControllerTest {
  private IHandler mockHandler;
  private ReviewMenuController reviewMenuController;
  private CommandInvoker mockInvoker;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockInvoker = mock(CommandInvoker.class);
    reviewMenuController = new ReviewMenuController(mockHandler, mockInvoker);
  }

  @Test
  @DisplayName("Caso 1 del menú de reseñas")
  void testAddRestaurant(){
    when(mockHandler.readLine()).thenReturn("1","0");

    reviewMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(1);
  }

  @Test
  @DisplayName("Caso 1 del menú de reseñas")
  void testAddReview(){
    when(mockHandler.readLine()).thenReturn("1","0");

    reviewMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(1);
  }

  @Test
  @DisplayName("Caso 2 del menú de reseñas")
  void testDisplayReviews(){
    when(mockHandler.readLine()).thenReturn("2","0");

    reviewMenuController.start();

    verify(mockInvoker, times(2)).printMenu();
    verify(mockHandler, times(2)).writeLine("Selecciona una opción (o ingresa 0 para regresar): ");

    verify(mockInvoker).executeCommand(2);
  }
}