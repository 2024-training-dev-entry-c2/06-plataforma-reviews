package org.example.command.menu;

import org.example.interfaces.IHandler;
import org.example.models.Menu;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ShowMenuTest {
  private IHandler mockHandler;
  private MenuService mockMenuService;
  private ShowMenu showMenuCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockMenuService = mock(MenuService.class);
    showMenuCommand = new ShowMenu(mockMenuService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para mostrar menú de restaurante")
  void testDisplayMenu(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles");
    Menu menu = new Menu("Menú navideño.");
    when(mockMenuService.getMenuByRestaurantName("Crepes & Waffles")).thenReturn(menu);
    showMenuCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante del cual deseas ver el menú: ");

    verify(mockMenuService).getMenuByRestaurantName("Crepes & Waffles");
  }

  @Test
  @DisplayName("Prueba fallida para mostrar menú de restaurante")
  void testFailDisplayMenu(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles");
    when(mockMenuService.getMenuByRestaurantName("Crepes & Waffles")).thenReturn(null);
    showMenuCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante del cual deseas ver el menú: ");

    verify(mockMenuService).getMenuByRestaurantName("Crepes & Waffles");
  }
}