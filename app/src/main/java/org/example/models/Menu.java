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
    System.out.println("\n------- Menú -------");
    for(Dish dish : dishes.values()){
      System.out.print("- ");
      dish.displayDish();
    }
    System.out.println("--------------------");
  }

  public void addDish(Dish dish){
    dishes.put(dish.getName(), dish);
  }

  public Dish searchDish(String name){
    return dishes.get(name);
  }

  public Map<String, Dish> getDishes() {
    return dishes;
  }
}
