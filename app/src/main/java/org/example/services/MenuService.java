package org.example.services;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;

public class MenuService {
  private RestaurantRepository repository;

  public MenuService() {
    this.repository = RestaurantRepository.getInstance();
  }

  public Boolean addMenu(String restaurantName, String menuName){
    if(repository.getRestaurantByName(restaurantName) == null){
      return false;
    }
    Menu menu = new Menu(menuName);
    repository.getRestaurantByName(restaurantName).setMenu(menu);
    return true;
  }

  public Menu getMenuByRestaurantName(String restaurantName){
    return repository.getRestaurantByName(restaurantName).getMenu();
  }

}

