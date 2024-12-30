package org.example.utils;

import org.example.Interface.ICommand;
import org.example.Interface.IConsoleHandler;
import org.example.constants.MenuOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;

public class AppMenuTest {

  private IConsoleHandler consoleHandler;
  private AppMenu appMenu;

  @BeforeEach
  public void setUp() {
    consoleHandler = Mockito.mock(IConsoleHandler.class);
    appMenu = new AppMenu(consoleHandler);
  }

  @Test
  public void testDisplayMenu() {
    appMenu.displayMenu();
    verify(consoleHandler).writeLine("Menú:");
    for (MenuOption option : MenuOption.values()) {
      verify(consoleHandler).writeLine(option.getOptionNumber() + ". " + option.getDescription());
    }
    verify(consoleHandler).writeLine("Seleccionad una opcion:");
  }

  @Test
  public void testCommandMapInitialization() {
    Map<MenuOption, ICommand> commandMap = AppMenu.commandMap;
    assertEquals(14, commandMap.size());

    assertTrue(commandMap.containsKey(MenuOption.CREATE_DISH));
    assertTrue(commandMap.containsKey(MenuOption.CREATE_RESTAURANT));
    assertTrue(commandMap.containsKey(MenuOption.ADD_DISH_TO_MENU));
    assertTrue(commandMap.containsKey(MenuOption.VIEW_ALL_RESTAURANTS));
    assertTrue(commandMap.containsKey(MenuOption.VIEW_DISH_REVIEWS));
    assertTrue(commandMap.containsKey(MenuOption.VIEW_RESTAURANT_REVIEWS));
    assertTrue(commandMap.containsKey(MenuOption.EDIT_RESTAURANT));
    assertTrue(commandMap.containsKey(MenuOption.DELETE_RESTAURANT));
    assertTrue(commandMap.containsKey(MenuOption.ASOCIATE_MENU_TO_RESTAURANT));
    assertTrue(commandMap.containsKey(MenuOption.EDIT_DISH));
    assertTrue(commandMap.containsKey(MenuOption.DELETE_DISH));
    assertTrue(commandMap.containsKey(MenuOption.VIEW_ALL_DISHES_MENU));
    assertTrue(commandMap.containsKey(MenuOption.AVERAGE_RATING_OF_DISH));
    assertTrue(commandMap.containsKey(MenuOption.AVERAGE_RATING_OF_RESTAURANT));
  }
}