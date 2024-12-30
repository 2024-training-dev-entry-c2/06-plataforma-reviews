package org.example.constants;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuOptionTest {

  @Test
  @DisplayName("Test Get Option Number")
  void testGetOptionNumber() {
    assertEquals(1, MenuOption.CREATE_DISH.getOptionNumber());
    assertEquals(2, MenuOption.CREATE_RESTAURANT.getOptionNumber());

  }

  @Test
  @DisplayName("Test Get Description")
  void testGetDescription() {
    assertEquals("Crear Plato", MenuOption.CREATE_DISH.getDescription());
    assertEquals("Crear Restaurante", MenuOption.CREATE_RESTAURANT.getDescription());

  }

  @Test
  @DisplayName("Test From Int")
  void testFromInt() {
    assertEquals(MenuOption.CREATE_DISH, MenuOption.fromInt(1));
    assertEquals(MenuOption.CREATE_RESTAURANT, MenuOption.fromInt(2));

  }

  @Test
  @DisplayName("Test From Int with Invalid Option")
  void testFromIntWithInvalidOption() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      MenuOption.fromInt(99);
    });
    assertEquals("Opcion no encontrada", exception.getMessage());
  }
}