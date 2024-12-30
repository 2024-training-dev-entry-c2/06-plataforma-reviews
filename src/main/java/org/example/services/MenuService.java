package org.example.services;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.repositories.MenuRepository;

import java.util.List;

public class MenuService {
  private MenuRepository menuRepository;

  public MenuService() {
    this.menuRepository = MenuRepository.getInstance();
  }

  public void addMenu(String name) {
    Menu menu = new Menu(name);
    menuRepository.addMenu(menu);
  }

  public List<Menu> getMenus() {
    return menuRepository.getMenus();
  }

  public void removeMenu(String name) {
    menuRepository.removeMenu(name);
  }

  public void addPlateToMenu(String nameMenu, Plate namePlate) {
    Menu menu = menuRepository.getMenus().stream()
            .filter(m -> m.getName().equalsIgnoreCase(nameMenu))
            .findFirst()
            .orElseThrow(() -> new RuntimeException("Menu no encontrado"));
    menu.addPlate(namePlate);
  }
}
