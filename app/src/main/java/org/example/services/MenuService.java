package org.example.services;

import org.example.Interface.IConsoleHandler;
import org.example.models.DishModel;
import org.example.models.MenuModel;
import org.example.models.RestaurantModel;
import org.example.repositories.DishRepository;
import org.example.repositories.RestaurantRepository;
import org.example.utils.ConsoleHandler;

import java.util.List;

public class MenuService {
  private final RestaurantRepository repositoryRestaurant;
  private final DishRepository repositoryDish;
  private final IConsoleHandler consoleHandler;

  public MenuService() {
    this.repositoryDish = DishRepository.getInstance();
    this.repositoryRestaurant = RestaurantRepository.getInstance();
    this.consoleHandler = new ConsoleHandler();
  }

  public void createMenuForRestaurant(String restaurantName, String menuName) {
    if (restaurantName == null) {
      throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo.");
    }
    if (menuName == null) {
      throw new IllegalArgumentException("El nombre del menú no puede ser nulo.");
    }
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    if (restaurant == null) {
      throw new IllegalArgumentException("Restaurante no encontrado: " + restaurantName);
    }
    if (restaurant.getMenu() != null) {
      throw new IllegalArgumentException("El restaurante ya tiene un menú.");
    }
    MenuModel menu = new MenuModel(restaurant, menuName);
    restaurant.setMenu(menu);
    repositoryRestaurant.associateMenuToRestaurant(restaurantName, menu);
  }

  public void addDishToMenu(String restaurantName, DishModel dish) {
    if (restaurantName == null) {
      throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo.");
    }
    if (dish == null) {
      throw new IllegalArgumentException("El plato no puede ser nulo.");
    }
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    DishModel existingDish = repositoryDish.getDish(dish.getName());
    if (restaurant == null || restaurant.getMenu() == null || existingDish == null) {
      throw new IllegalArgumentException("Restaurante, menú o plato no encontrado.");
    }
    restaurant.getMenu().addDish(dish);
    repositoryDish.addDishToMenu(restaurantName, dish);
  }

  public void editDishInMenu(String restaurantName, String dishName, DishModel updatedDish) {
    if (restaurantName == null) {
      throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo.");
    }
    if (dishName == null) {
      throw new IllegalArgumentException("El nombre del plato no puede ser nulo.");
    }
    if (updatedDish == null) {
      throw new IllegalArgumentException("El plato actualizado no puede ser nulo.");
    }
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado.");
    }
    MenuModel menu = restaurant.getMenu();
    List<DishModel> dishes = menu.getDishes();
    for (int i = 0; i < dishes.size(); i++) {
      if (dishes.get(i).getName().equalsIgnoreCase(dishName)) {
        dishes.set(i, updatedDish);
        repositoryDish.editDishInMenu(restaurantName, dishName, updatedDish);
        return;
      }
    }
    throw new IllegalArgumentException("Plato no encontrado: " + dishName);
  }

  public void removeDishFromMenu(String restaurantName, String dishName) {
    if (restaurantName == null) {
      throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo.");
    }
    if (dishName == null) {
      throw new IllegalArgumentException("El nombre del plato no puede ser nulo.");
    }
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    if (restaurant == null || restaurant.getMenu() == null) {
      throw new IllegalArgumentException("Restaurante o menú no encontrado.");
    }
    DishModel dish = repositoryDish.getDish(dishName);
    if (dish == null) {
      throw new IllegalArgumentException("Plato no encontrado: " + dishName);
    }

    restaurant.getMenu().removeDish(dish);
    repositoryDish.removeDishFromMenu(restaurantName, dishName);
  }

  public MenuModel getMenuOfRestaurant(String restaurantName) {
    if (restaurantName == null) {
      throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo.");
    }
    RestaurantModel restaurant = repositoryRestaurant.getRestaurant(restaurantName);
    return restaurant != null ? restaurant.getMenu() : null;
  }

  public void getDishesInMenu(String restaurantName) {
    if (restaurantName == null) {
      throw new IllegalArgumentException("El nombre del restaurante no puede ser nulo.");
    }
    MenuModel menu = getMenuOfRestaurant(restaurantName);
    if (menu == null) {
      throw new IllegalArgumentException("Menu no encontrado: " + restaurantName);
    }

    List<DishModel> dishes = menu.getDishes();
    for (DishModel dish : dishes) {
      if (dish.getName() == null) {
        throw new IllegalArgumentException("El nombre del plato no puede ser nulo.");
      }
      consoleHandler.writeLine("Plato: " + dish.getName() + ", Descripción: " + dish.getDescription() + ", Precio: " + dish.getPrice());
    }
  }
  }
