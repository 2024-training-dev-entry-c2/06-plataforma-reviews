package org.example.controllers;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.services.MenuService;

import java.util.List;

public class MenuController {
  private MenuService menuService;

  public MenuController() {
    this.menuService = new MenuService();
  }


  public void addMenu(String name) {
    menuService.addMenu(name);
    System.out.println("Menu agregado exitosamente.");
  }

  public void getMenus() {
    List<Menu> menus = menuService.getMenus();
    if (menus.isEmpty()) {
      System.out.println("No hay menus registrados.");
    } else {
      System.out.println("Menus registrados:");
      menus.forEach(System.out::println);
    }
  }

  public void setMenuService(MenuService menuService) {
    this.menuService = menuService;
  }

  public void removeMenu(String name) {
    menuService.removeMenu(name);
    System.out.println("Menu eliminado exitosamente.");
  }

  public void addPlateToMenu(String nameMenu, Plate namePlate) {
    menuService.addPlateToMenu(nameMenu, namePlate);
    System.out.println("Plato agregado exitosamente.");
  }
}
