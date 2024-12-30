package org.example.controllers;

import org.example.models.DishModel;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.mockito.Mockito.*;

class MenuControllerTest {

  private MenuController menuController;
  private MenuService mockMenuService;

  @BeforeEach
  void setup() throws Exception {
    mockMenuService = mock(MenuService.class);
    menuController = new MenuController();

    Field menuServiceField = MenuController.class.getDeclaredField("menuService");
    menuServiceField.setAccessible(true);
    menuServiceField.set(menuController, mockMenuService);
  }

  @Test
  @DisplayName("Test Associate Menu To Restaurant")
  void testAssociateMenuToRestaurant() {
    menuController.associateMenuToRestaurant("Restaurant 1", "Menu 1");
    verify(mockMenuService).createMenuForRestaurant("Restaurant 1", "Menu 1");
  }

  @Test
  @DisplayName("Test Add Dish To Menu")
  void testAddDishToMenu() {
    DishModel dish = new DishModel("Dish 1", "Description", 10.0);
    menuController.addDishToMenu("Restaurant 1", dish);
    verify(mockMenuService).addDishToMenu("Restaurant 1", dish);
  }

  @Test
  @DisplayName("Test Edit Dish In Menu")
  void testEditDishInMenu() {
    DishModel updatedDish = new DishModel("Dish 1", "New Description", 15.0);
    menuController.editDishInMenu("Restaurant 1", "Dish 1", updatedDish);
    verify(mockMenuService).editDishInMenu("Restaurant 1", "Dish 1", updatedDish);
  }

  @Test
  @DisplayName("Test Remove Dish From Menu")
  void testRemoveDishFromMenu() {
    menuController.removeDishFromMenu("Restaurant 1", "Dish 1");
    verify(mockMenuService).removeDishFromMenu("Restaurant 1", "Dish 1");
  }

  @Test
  @DisplayName("Test Get Dishes In Menu")
  void testGetDishesInMenu() {
    menuController.getDishesInMenu("Restaurant 1");
    verify(mockMenuService).getDishesInMenu("Restaurant 1");
  }
}