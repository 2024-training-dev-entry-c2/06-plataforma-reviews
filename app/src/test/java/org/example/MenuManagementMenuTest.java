package org.example;

import org.example.services.MenuService;
import org.example.utils.IHandler;
import org.example.utils.MenuManagementMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MenuManagementMenuTest {
  private IHandler mockHandler;
  private MenuService mockService;
  private MenuManagementMenu menuManagementMenu;

  @BeforeEach
  void setUp() {
    mockHandler = mock(IHandler.class);
    mockService = mock(MenuService.class);
    menuManagementMenu = new MenuManagementMenu(mockService, mockHandler);
  }

  @Test
  @DisplayName("Caso 1 del menu")
  void testAddDishMenu() {
    when(mockHandler.readLine()).thenReturn("1", "2","Clásico", "2 Presas apanadas, ensalada de repollo personal", "22900","4");
    menuManagementMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del menu");
    verify(mockHandler).writeLine("Ingrese el nombre del plato");
    verify(mockHandler).writeLine("Ingrese la descripción");
    verify(mockHandler).writeLine("Ingrese el precio");
    verify(mockService).addDishMenu(2L, "Clásico", "2 Presas apanadas, ensalada de repollo personal", 22900L);
  }

  @Test
  @DisplayName("Caso 2 del menu")
  void testUpdateDishMenu() {
    when(mockHandler.readLine()).thenReturn("2", "1", "2","Clásico", "2 Presas apanadas, ensalada de repollo personal", "20900","4");
    menuManagementMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del menu");
    verify(mockHandler).writeLine("Ingrese el ID del plato");
    verify(mockHandler).writeLine("Ingrese el nombre del plato");
    verify(mockHandler).writeLine("Ingrese la descripción");
    verify(mockHandler).writeLine("Ingrese el precio");
    verify(mockService).updateDishMenu(2L, "Clásico", "2 Presas apanadas, ensalada de repollo personal", 20900L);
  }

  @Test
  @DisplayName("Caso 3 del menu")
  void testRemoveDishMenu() {
    when(mockHandler.readLine()).thenReturn("3", "2","1","4");
    menuManagementMenu.displayMenu();
    verify(mockHandler).writeLine("Ingrese el ID del menu");
    verify(mockHandler).writeLine("Ingrese el ID del plato");
    verify(mockService).removeDishMenu(1L, 2L);
  }

  @Test
  @DisplayName("Caso 4 del menu")
  void testExitMenuManagement() {
    when(mockHandler.readLine()).thenReturn("4");
    menuManagementMenu.displayMenu();
    verify(mockHandler).writeLine("Saliendo de opciones de menu");
  }

  @Test
  @DisplayName("Caso inválido del menu")
  void testInvalidOption() {
    when(mockHandler.readLine()).thenReturn("7","4");
    menuManagementMenu.displayMenu();
    verify(mockHandler).writeLine("Opción inválida");
  }

}
