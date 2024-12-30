package org.example.repositories;

import org.example.models.Dish;

import java.util.HashMap;
import java.util.Map;

public class DishRepository {
  private static DishRepository instance;
  private Map<Long, Dish> dishes;

  private DishRepository() {
    this.dishes = new HashMap<>();
  }

  public static synchronized DishRepository getInstance() {
    if (instance == null) {
      instance = new DishRepository();
    }
    return instance;
  }

  public void addDish(Long id,Long menuId, String name, String description, Long price) {
    this.dishes.put(id, new Dish(id, menuId, name, description, price));
  }

  public void updateDish( Dish dish,String name, String description, Long price) {
    dish.setName(name.isEmpty() ? dish.getName() : name);
    dish.setDescription(description.isEmpty() ? dish.getDescription() : description);
    dish.setPrice(price);
  }

  public Boolean removeDish(Long id) {
    return this.dishes.remove(id) != null;
  }

  public Dish getDish(Long id) {
    return this.dishes.get(id);
  }
}
