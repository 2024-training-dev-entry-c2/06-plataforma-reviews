package org.example.models;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MenuModelTest {

  @Test
  @DisplayName("Test MenuModel Get Name")
  void testMenuModelGetName() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");

    assertEquals("Menú Principal", menu.getName());
  }

  @Test
  @DisplayName("Test MenuModel Set Name")
  void testMenuModelSetName() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");

    menu.setName("Nuevo Menú");
    assertEquals("Nuevo Menú", menu.getName());
  }

  @Test
  @DisplayName("Test MenuModel Get Dishes")
  void testMenuModelGetDishes() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");

    assertTrue(menu.getDishes().isEmpty());
  }

  @Test
  @DisplayName("Test MenuModel Add Dish")
  void testMenuModelAddDish() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);

    menu.addDish(dish);

    assertEquals(1, menu.getDishes().size());
    assertEquals(dish, menu.getDishes().get(0));
  }

  @Test
  @DisplayName("Test MenuModel Remove Dish")
  void testMenuModelRemoveDish() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");
    DishModel dish = new DishModel("Plato 1", "Descripción", 10.0);

    menu.addDish(dish);
    menu.removeDish(dish);

    assertTrue(menu.getDishes().isEmpty());
  }

  @Test
  @DisplayName("Test MenuModel Get Restaurant")
  void testMenuModelGetRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menú Principal");

    assertEquals(restaurant, menu.getRestaurant());
  }
}