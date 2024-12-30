package org.example.services;

import org.example.models.Dish;
import org.example.repositories.MenuRepository;

public class MenuService {
  private final MenuRepository menuRepository;

  public MenuService(MenuRepository menuRepository) {
    this.menuRepository = menuRepository;
  }

  public void addDishMenu(Long menuId, String name, String description, Long price) {
    if(this.menuRepository.getMenu(menuId) != null) {
      this.menuRepository.addDishMenu(menuId, name, description, price);
      System.out.println("Se ha añadido el plato " + name);
    } else {
      System.out.println("El menu con ID " + menuId + " no existe.");
    }
  }

  public void updateDishMenu( Long dishId, String name, String description, Long price){
    Dish dish = this.menuRepository.getDish(dishId);
    if(dish != null) {
      this.menuRepository.updateDishMenu(dish, name, description, price);
      System.out.println("Se ha actualizado el plato " + name);
    } else {
      System.out.println("El plato con ID " + dishId + " no existe.");
    }
  }

  public void displayMenus() {
    System.out.println("\nMenus disponibles:");
    this.menuRepository.displayMenus();
  }

  public void displayMenu(Long menuId) {
    if(this.menuRepository.getMenu(menuId) != null) {
      System.out.println("\nPlatos disponibles:");
      this.menuRepository.getDishesMenu(menuId).forEach(dish -> System.out.println("\n" + dish.getId() + ". " + dish.toString()));
    } else {
      System.out.println("El menu con ID " + menuId + " no existe.");
    }
  }

  public void removeDishMenu(Long dishId, Long menuId) {
    if(this.menuRepository.removeDishMenu(dishId, menuId)) {
      System.out.println("Se ha eliminado el plato con ID " + dishId);
    } else {
      System.out.println("El plato con ID " + dishId + " no existe.");
    }
  }
}
