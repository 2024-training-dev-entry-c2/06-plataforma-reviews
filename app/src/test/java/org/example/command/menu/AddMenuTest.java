package org.example.command.menu;

import org.example.interfaces.IHandler;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class AddMenuTest {
  private IHandler mockHandler;
  private MenuService mockMenuService;
  private AddMenu addMenuCommand;

  @BeforeEach
  void setup(){
    mockHandler = mock(IHandler.class);
    mockMenuService = mock(MenuService.class);
    addMenuCommand = new AddMenu(mockMenuService, mockHandler);
  }

  @Test
  @DisplayName("Prueba exitosa para agregar menú a restaurante")
  void testAddMenu(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Menú navideño");
    when(mockMenuService.addMenu("Crepes & Waffles","Menú navideño")).thenReturn(true);

    addMenuCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante al cual deseas añadir el menú: ");
    verify(mockHandler).writeLine("Ingresa el nombre del menú: ");

    verify(mockMenuService).addMenu("Crepes & Waffles","Menú navideño");
  }

  @Test
  @DisplayName("Prueba fallida para agregar menú a restaurante")
  void testAddFailMenu(){
    when(mockHandler.readLine()).thenReturn("Crepes & Waffles","Menú navideño");
    when(mockMenuService.addMenu("Crepes & Waffles","Menú navideño")).thenReturn(false);

    addMenuCommand.execute();


    verify(mockHandler).writeLine("Indica el nombre del restaurante al cual deseas añadir el menú: ");
    verify(mockHandler).writeLine("Ingresa el nombre del menú: ");

    verify(mockMenuService).addMenu("Crepes & Waffles","Menú navideño");
  }

}