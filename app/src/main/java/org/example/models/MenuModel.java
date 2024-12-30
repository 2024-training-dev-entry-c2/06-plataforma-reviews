package org.example.models;

import java.util.LinkedList;
import java.util.List;

public class MenuModel {
  private String name;
  private List<DishModel> dishes;
  private RestaurantModel restaurant;

  public MenuModel(RestaurantModel restaurant, String name) {
    this.dishes = new LinkedList<>();
    this.restaurant = restaurant;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<DishModel> getDishes() {
    return dishes;
  }

  public RestaurantModel getRestaurant() {
    return restaurant;
  }

  public void addDish(DishModel dish) {
    this.dishes.add(dish);
  }

  public void removeDish(DishModel dish) {
    this.dishes.remove(dish);
  }
}
