package org.example.services;

import org.example.models.DishModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.repositories.DataRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

  private MenuService menuService;
  private DataRepository mockRepository;

  @BeforeEach
  void setup() throws Exception {
    mockRepository = mock(DataRepository.class);
    setMockInstance(mockRepository);
    menuService = new MenuService();
  }

  private void setMockInstance(DataRepository mockRepository) throws Exception {
    Field instanceField = DataRepository.class.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, mockRepository);
  }

  @Test
  @DisplayName("Test Create Menu For Restaurant")
  void testCreateMenuForRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    menuService.createMenuForRestaurant("Restaurante 1", "Menu 1");

    assertNotNull(restaurant.getMenu());
    assertEquals("Menu 1", restaurant.getMenu().getName());
    verify(mockRepository).associateMenuToRestaurant("Restaurante 1", restaurant.getMenu());
  }

  @Test
  @DisplayName("Test Add Dish To Menu")
  void testAddDishToMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    when(mockRepository.getDish("Plato 1")).thenReturn(dish);

    menuService.addDishToMenu("Restaurante 1", dish);

    assertTrue(menu.getDishes().contains(dish));
    verify(mockRepository).addDishToMenu("Restaurante 1", dish);
  }

  @Test
  @DisplayName("Test Edit Dish In Menu")
  void testEditDishInMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);
    DishModel updatedDish = new DishModel("Plato 1", "Nueva descripción", 12.0);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    menuService.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);

    assertTrue(menu.getDishes().contains(updatedDish));
    verify(mockRepository).editDishInMenu("Restaurante 1", "Plato 1", updatedDish);
  }

  @Test
  @DisplayName("Test Remove Dish From Menu")
  void testRemoveDishFromMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    when(mockRepository.getDish("Plato 1")).thenReturn(dish);

    menuService.removeDishFromMenu("Restaurante 1", "Plato 1");

    assertFalse(menu.getDishes().contains(dish));
    verify(mockRepository).removeDishFromMenu("Restaurante 1", "Plato 1");
  }

  @Test
  @DisplayName("Test Get Menu Of Restaurant")
  void testGetMenuOfRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    MenuModel result = menuService.getMenuOfRestaurant("Restaurante 1");

    assertEquals(menu, result);
  }

  @Test
  @DisplayName("Test Get Dishes In Menu")
  void testGetDishesInMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);
    restaurant.setMenu(menu);

    when(mockRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    menuService.getDishesInMenu("Restaurante 1");

    verify(mockRepository).getRestaurant("Restaurante 1");
  }
}