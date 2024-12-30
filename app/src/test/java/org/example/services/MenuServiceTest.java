package org.example.services;

import org.example.models.DishModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.repositories.DishRepository;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

  private MenuService menuService;
  private DishRepository mockDishRepository;
  private RestaurantRepository mockRestaurantRepository;

  @BeforeEach
  void setup() throws Exception {
    mockDishRepository = mock(DishRepository.class);
    mockRestaurantRepository = mock(RestaurantRepository.class);
    setMockInstance(DishRepository.class, mockDishRepository);
    setMockInstance(RestaurantRepository.class, mockRestaurantRepository);
    menuService = new MenuService();
  }

  private void setMockInstance(Class<?> clazz, Object mockInstance) throws Exception {
    Field instanceField = clazz.getDeclaredField("instance");
    instanceField.setAccessible(true);
    instanceField.set(null, mockInstance);
  }

  @Test
  @DisplayName("Test Create Menu For Restaurant")
  void testCreateMenuForRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    menuService.createMenuForRestaurant("Restaurante 1", "Menu 1");

    assertNotNull(restaurant.getMenu());
    assertEquals("Menu 1", restaurant.getMenu().getName());
    verify(mockRestaurantRepository).associateMenuToRestaurant("Restaurante 1", restaurant.getMenu());
  }

  @Test
  @DisplayName("Test Create Menu For Nonexistent Restaurant")
  void testCreateMenuForNonexistentRestaurant() {
    when(mockRestaurantRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);
    assertThrows(IllegalArgumentException.class, () -> menuService.createMenuForRestaurant("Nonexistent Restaurant", "Menu 1"));
  }

  @Test
  @DisplayName("Test Create Menu For Restaurant With Existing Menu")
  void testCreateMenuForRestaurantWithExistingMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel existingMenu = new MenuModel(restaurant, "Existing Menu");
    restaurant.setMenu(existingMenu);
    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    assertThrows(IllegalArgumentException.class, () -> menuService.createMenuForRestaurant("Restaurante 1", "Menu 1"));
  }

  @Test
  @DisplayName("Test Add Dish To Menu")
  void testAddDishToMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);

    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    when(mockDishRepository.getDish("Plato 1")).thenReturn(dish);

    menuService.addDishToMenu("Restaurante 1", dish);

    assertTrue(menu.getDishes().contains(dish));
    verify(mockDishRepository).addDishToMenu("Restaurante 1", dish);
  }

  @Test
  @DisplayName("Test Add Dish To Menu Nonexistent Restaurant")
  void testAddDishToMenuNonexistentRestaurant() {
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    when(mockRestaurantRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> menuService.addDishToMenu("Nonexistent Restaurant", dish));
  }

  @Test
  @DisplayName("Test Add Dish To Menu Nonexistent Dish")
  void testAddDishToMenuNonexistentDish() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    when(mockDishRepository.getDish("Nonexistent Dish")).thenReturn(null);

    DishModel dish = new DishModel("Nonexistent Dish", "Descripción del plato", 10.0);
    assertThrows(IllegalArgumentException.class, () -> menuService.addDishToMenu("Restaurante 1", dish));
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

    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    menuService.editDishInMenu("Restaurante 1", "Plato 1", updatedDish);

    assertTrue(menu.getDishes().contains(updatedDish));
    verify(mockDishRepository).editDishInMenu("Restaurante 1", "Plato 1", updatedDish);
  }

  @Test
  @DisplayName("Test Edit Dish In Menu Nonexistent Restaurant")
  void testEditDishInMenuNonexistentRestaurant() {
    DishModel updatedDish = new DishModel("Plato 1", "Nueva descripción", 12.0);
    when(mockRestaurantRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> menuService.editDishInMenu("Nonexistent Restaurant", "Plato 1", updatedDish));
  }

  @Test
  @DisplayName("Test Edit Dish In Menu Nonexistent Dish")
  void testEditDishInMenuNonexistentDish() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    DishModel updatedDish = new DishModel("Nonexistent Dish", "Nueva descripción", 12.0);
    assertThrows(IllegalArgumentException.class, () -> menuService.editDishInMenu("Restaurante 1", "Nonexistent Dish", updatedDish));
  }

  @Test
  @DisplayName("Test Remove Dish From Menu")
  void testRemoveDishFromMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);

    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    when(mockDishRepository.getDish("Plato 1")).thenReturn(dish);

    menuService.removeDishFromMenu("Restaurante 1", "Plato 1");

    assertFalse(menu.getDishes().contains(dish));
    verify(mockDishRepository).removeDishFromMenu("Restaurante 1", "Plato 1");
  }

  @Test
  @DisplayName("Test Remove Dish From Menu Nonexistent Restaurant")
  void testRemoveDishFromMenuNonexistentRestaurant() {
    when(mockRestaurantRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> menuService.removeDishFromMenu("Nonexistent Restaurant", "Plato 1"));
  }

  @Test
  @DisplayName("Test Remove Dish From Menu Nonexistent Dish")
  void testRemoveDishFromMenuNonexistentDish() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);
    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    when(mockDishRepository.getDish("Nonexistent Dish")).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> menuService.removeDishFromMenu("Restaurante 1", "Nonexistent Dish"));
  }

  @Test
  @DisplayName("Test Get Menu Of Restaurant")
  void testGetMenuOfRestaurant() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    restaurant.setMenu(menu);

    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    MenuModel result = menuService.getMenuOfRestaurant("Restaurante 1");

    assertEquals(menu, result);
  }

  @Test
  @DisplayName("Test Get Menu Of Nonexistent Restaurant")
  void testGetMenuOfNonexistentRestaurant() {
    when(mockRestaurantRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);

    MenuModel result = menuService.getMenuOfRestaurant("Nonexistent Restaurant");

    assertNull(result);
  }

  @Test
  @DisplayName("Test Get Dishes In Menu")
  void testGetDishesInMenu() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    MenuModel menu = new MenuModel(restaurant, "Menu 1");
    DishModel dish = new DishModel("Plato 1", "Descripción del plato", 10.0);
    menu.addDish(dish);
    restaurant.setMenu(menu);

    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);

    menuService.getDishesInMenu("Restaurante 1");

    verify(mockRestaurantRepository).getRestaurant("Restaurante 1");
  }

  @Test
  @DisplayName("Test Get Dishes In Menu Nonexistent Restaurant")
  void testGetDishesInMenuNonexistentRestaurant() {
    when(mockRestaurantRepository.getRestaurant("Nonexistent Restaurant")).thenReturn(null);

    assertThrows(IllegalArgumentException.class, () -> menuService.getDishesInMenu("Nonexistent Restaurant"));
  }

  @Test
  @DisplayName("Test Create Menu For Restaurant With Null Name")
  void testCreateMenuForRestaurantWithNullName() {
    assertThrows(IllegalArgumentException.class, () -> menuService.createMenuForRestaurant(null, "Menu 1"));
  }

  @Test
  @DisplayName("Test Create Menu For Restaurant With Null Menu Name")
  void testCreateMenuForRestaurantWithNullMenuName() {
    RestaurantModel restaurant = new RestaurantModel("Restaurante 1", "Calle Ficticia 123", true);
    when(mockRestaurantRepository.getRestaurant("Restaurante 1")).thenReturn(restaurant);
    assertThrows(IllegalArgumentException.class, () -> menuService.createMenuForRestaurant("Restaurante 1", null));
  }

  @Test
  @DisplayName("Test Add Dish To Menu With Null Dish")
  void testAddDishToMenuWithNullDish() {
    assertThrows(IllegalArgumentException.class, () -> menuService.addDishToMenu("Restaurante 1", null));
  }

  @Test
  @DisplayName("Test Edit Dish In Menu With Null Updated Dish")
  void testEditDishInMenuWithNullUpdatedDish() {
    assertThrows(IllegalArgumentException.class, () -> menuService.editDishInMenu("Restaurante 1", "Plato 1", null));
  }

  @Test
  @DisplayName("Test Remove Dish From Menu With Null Dish Name")
  void testRemoveDishFromMenuWithNullDishName() {
    assertThrows(IllegalArgumentException.class, () -> menuService.removeDishFromMenu("Restaurante 1", null));
  }

  @Test
  @DisplayName("Test Get Dishes In Menu With Null Restaurant Name")
  void testGetDishesInMenuWithNullRestaurantName() {
    assertThrows(IllegalArgumentException.class, () -> menuService.getDishesInMenu(null));
  }
}