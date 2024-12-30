package org.example.controllers;

import org.example.models.DishModel;
import org.example.services.MenuService;

public class MenuController {
  private final MenuService menuService;

  public MenuController() {
    this.menuService = new MenuService();
  }

  public void associateMenuToRestaurant(String restaurantName, String menuName) {
    menuService.createMenuForRestaurant(restaurantName, menuName);
  }

  public void addDishToMenu(String restaurantName, DishModel dish) {
    menuService.addDishToMenu(restaurantName, dish);
  }

  public void editDishInMenu(String restaurantName, String dishName, DishModel updatedDish) {
    menuService.editDishInMenu(restaurantName, dishName, updatedDish);
  }

  public void removeDishFromMenu(String restaurantName, String dishName) {
    menuService.removeDishFromMenu(restaurantName, dishName);
  }

  public void getDishesInMenu(String restaurantName) {
    menuService.getDishesInMenu(restaurantName);
  }
}
