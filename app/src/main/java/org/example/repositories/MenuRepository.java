package org.example.repositories;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MenuRepository {
  private static MenuRepository instance;
  private DishRepository dishRepository;
  private RestaurantRepository restaurantRepository;
  private Map<Long, Menu> menus;
  private Long currentDishId = 0L;

  private MenuRepository() {
    this.menus = new HashMap<>();
    this.dishRepository = DishRepository.getInstance();
  }

  public static synchronized MenuRepository getInstance() {
    if (instance == null) {
      instance = new MenuRepository();
    }
    return instance;
  }

  public void addMenu(Long menuId) {
    this.menus.put(menuId, new Menu(menuId));
  }

  public void addDishMenu(Long menuId, String name, String description, Long price) {
    this.currentDishId++;
    this.dishRepository.addDish(this.currentDishId, menuId, name, description, price);
    Set<Long> dishIds = this.menus.get(menuId).getDishIds();
    dishIds.add(this.currentDishId);
  }

  public void updateDishMenu(Dish dish, String name, String description, Long price) {
    this.dishRepository.updateDish(dish, name, description, price);
  }

  public Boolean removeDishMenu(Long dishId, Long menuId) {
    Set<Long> dishIds = this.menus.get(menuId).getDishIds();
    dishIds.remove(dishId);
    return this.dishRepository.removeDish(dishId);
  }

  public void removeMenu(Long menuId) {
    Set<Long> dishIds = this.menus.get(menuId).getDishIds();
    dishIds.forEach(dishId -> this.dishRepository.removeDish(dishId));
    this.menus.remove(menuId);
  }

  public List<Dish> getDishesMenu(Long menuId) {
    Set<Long> dishIds = this.menus.get(menuId).getDishIds();
    return dishIds.stream().map(dishId -> this.dishRepository.getDish(dishId)).toList();
  }

  public void setRestaurantRepository() {
    if (this.restaurantRepository == null) {
      this.restaurantRepository = RestaurantRepository.getInstance();
    }
  }

  public void displayMenus() {
    setRestaurantRepository();
    Map<Long, Restaurant> restaurants = this.restaurantRepository.getRestaurants();
    this.menus.forEach((id, menu) -> System.out.println("\n" + id + ". Menú restaurante " + restaurants.get(id).getName()));
  }

  public Dish getDish(Long dishId) {
    return this.dishRepository.getDish(dishId);
  }

  public Menu getMenu(Long menuId) {
    return this.menus.get(menuId);
  }
}
