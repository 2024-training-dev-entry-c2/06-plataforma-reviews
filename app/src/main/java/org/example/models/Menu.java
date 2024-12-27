package org.example.models;

import java.util.HashMap;
import java.util.Map;

public class Menu {
  private String name;
  private Map<String, Dish> dishes;

  public Menu(String name) {
    this.name = name;
    this.dishes = new HashMap<>();
  }

  public void displayMenu(){
    System.out.println("\n------- Menu -------");
    for(Dish dish : dishes.values()){
      dish.displayDish();
    }
    System.out.println("--------------------");
  }

  public void addDish(Dish dish){
    dishes.put(dish.getName(), dish);
  }
}
