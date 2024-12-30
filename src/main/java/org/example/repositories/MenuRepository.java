package org.example.repositories;

import org.example.models.Menu;

import java.util.ArrayList;
import java.util.List;

public class MenuRepository {
  private static MenuRepository instance;
  private List<Menu> menus;

  private MenuRepository(){
    this.menus = new ArrayList<>();
  }

  public static MenuRepository getInstance(){
    if (instance == null){
      instance = new MenuRepository();
    }
    return instance;
  }

  public void addMenu(Menu menu){
    menus.add(menu);
  }

  public List<Menu> getMenus(){
    return new ArrayList<>(menus);
  }

  public void removeMenu(String name){
    menus.removeIf(menu -> menu.getName().equalsIgnoreCase(name));
  }
}
