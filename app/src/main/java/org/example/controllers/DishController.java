package org.example.controllers;

import org.example.services.DishService;

public class DishController  {
  private final DishService dishService;


  public DishController() {
    this.dishService = new DishService();

  }

  public void createDish(String name, String description, Double price) {
    dishService.createDish(name, description, price);
  }

  public Double getAverageRatingOfDish(String dishName) {
    return dishService.getAverageRatingOfDish(dishName);
  }

}
