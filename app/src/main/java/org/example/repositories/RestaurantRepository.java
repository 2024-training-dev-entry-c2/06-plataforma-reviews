package org.example.repositories;

import org.example.models.Restaurant;

import java.util.HashMap;
import java.util.Map;

public class RestaurantRepository {
  private static RestaurantRepository instance;
  private MenuRepository menuRepository;
  private Map<Long, Restaurant> restaurants;
  private Long currentId = 0L;

  private RestaurantRepository() {
    this.restaurants = new HashMap<>();
  }

  public static synchronized RestaurantRepository getInstance() {
    if (instance == null) {
      instance = new RestaurantRepository();
    }
    return instance;
  }

  public void setMenuRepository() {
    if (this.menuRepository == null) {
      this.menuRepository = MenuRepository.getInstance();
    }
  }

  public void addRestaurant(String name, String phone, String description) {
    this.currentId++;
    Restaurant restaurant = new Restaurant(this.currentId, name, phone, description);
    this.restaurants.put(this.currentId, restaurant);
    setMenuRepository();
    this.menuRepository.addMenu(this.currentId);
  }

  public void updateRestaurant(Restaurant restaurant, String name, String phone, String description) {
      restaurant.setName(name.isEmpty() ? restaurant.getName() : name);
      restaurant.setPhone(phone.isEmpty() ? restaurant.getPhone() : phone);
      restaurant.setDescription(description.isEmpty() ? restaurant.getDescription() : description);
  }

  public Boolean removeRestaurant(Long id) {
    setMenuRepository();
    this.menuRepository.removeMenu(id);
    return this.restaurants.remove(id) != null;
  }

  public Map<Long, Restaurant> getRestaurants() {
    return restaurants;
  }
}
