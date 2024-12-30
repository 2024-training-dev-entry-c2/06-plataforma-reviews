package org.example.services;

import org.example.Interface.IConsoleHandler;
import org.example.models.DishModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.repositories.DataRepository;
import org.example.utils.ConsoleHandler;

import java.util.List;

public class MenuService {
  private final DataRepository repository;
  private final IConsoleHandler consoleHandler;

  public MenuService() {
    this.repository = DataRepository.getInstance();
    this.consoleHandler = new ConsoleHandler();
  }

  public void createMenuForRestaurant(String restaurantName, String menuName) {
    RestaurantModel restaurant = repository.getRestaurant(restaurantName);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
    }
    if (restaurant.getMenu() != null) {
      throw new IllegalArgumentException("El restaurante ya tiene un menú.");
    }
    MenuModel menu = new MenuModel(restaurant, menuName);
    restaurant.setMenu(menu);
    repository.associateMenuToRestaurant(restaurantName, menu);
  }

  public void addDishToMenu(String restaurantName, DishModel dish) {
    RestaurantModel restaurant = repository.getRestaurant(restaurantName);
    DishModel existingDish = repository.getDish(dish.getName());
    if (restaurant == null || restaurant.getMenu() == null || existingDish == null) {
      throw new IllegalArgumentException("Restaurante, menú o plato no encontrado.");
    }
    restaurant.getMenu().addDish(dish);
    repository.addDishToMenu(restaurantName, dish);
  }

  public void editDishInMenu(String restaurantName, String dishName, DishModel updatedDish) {
    RestaurantModel restaurant = repository.getRestaurant(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado.");
    }
    MenuModel menu = restaurant.getMenu();
    List<DishModel> dishes = menu.getDishes();
    for (int i = 0; i < dishes.size(); i++) {
      if (dishes.get(i).getName().equalsIgnoreCase(dishName)) {
        dishes.set(i, updatedDish);
        repository.editDishInMenu(restaurantName,dishName,updatedDish);
        return;
      }
    }
  }

  public void removeDishFromMenu(String restaurantName, String dishName) {
    RestaurantModel restaurant = repository.getRestaurant(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado.");
    }
    DishModel dish = repository.getDish(dishName);
    if (dish == null) {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }

    restaurant.getMenu().removeDish(dish);
    repository.removeDishFromMenu(restaurantName,dishName);
  }

  public MenuModel getMenuOfRestaurant(String restaurantName) {
    RestaurantModel restaurant = repository.getRestaurant(restaurantName);
    return restaurant != null ? restaurant.getMenu() : null;
  }

  public void getDishesInMenu(String restaurantName) {
    MenuModel menu = getMenuOfRestaurant(restaurantName);
    if (menu == null) {
      throw new IllegalArgumentException("Menu no encontrado: " + restaurantName);
    }

    List<DishModel> dishes = menu.getDishes();
    for (DishModel dish : dishes) {
      consoleHandler.writeLine("Plato: " + dish.getName() + ", Descripción: " + dish.getDescription() + ", Precio: " + dish.getPrice());
    }
  }

}