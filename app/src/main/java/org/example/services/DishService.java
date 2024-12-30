package org.example.services;

import org.example.models.Dish;
import org.example.repositories.RestaurantRepository;

public class DishService {
  private RestaurantRepository repository;

  public DishService() {
    this.repository = RestaurantRepository.getInstance();
  }

  // Para pruebas
  public DishService(RestaurantRepository repository) {
    this.repository = repository;
  }

  public Boolean addDish(String restaurantName, String dishName, String dishDescription, Float dishPrice ){
    if(repository.getRestaurantByName(restaurantName) == null){
      return false;
    }
    Dish dish = new Dish(dishName, dishDescription, dishPrice);
    repository.getRestaurantByName(restaurantName).getMenu().addDish(dish);
    return true;
  }

  public Boolean removeDish(String restaurantName, Dish dish){
    if(repository.getRestaurantByName(restaurantName) == null){
      return false;
    }
    if( repository.getRestaurantByName(restaurantName).getMenu().getDishes().get(dish.getName()) == null){
      return false;
    }
    repository.getRestaurantByName(restaurantName).getMenu().removeDish(dish);
    return true;
  }

  public Boolean editDish(String restaurantName, Dish dish, String dishName, String dishDescription, Float dishPrice){
    if(repository.getRestaurantByName(restaurantName) == null){
      return false;
    }
    if( repository.getRestaurantByName(restaurantName).getMenu().getDishes().get(dish.getName()) == null){
      return false;
    }
    repository.getRestaurantByName(restaurantName).getMenu().getDishes().get(dish.getName()).setDescription(dishDescription);
    repository.getRestaurantByName(restaurantName).getMenu().getDishes().get(dish.getName()).setPrice(dishPrice);
    return true;
  }

  public Dish getDishByName(String restaurantName,String dishName){
    return repository.getRestaurantByName(restaurantName).getMenu().searchDish(dishName);
  }
}
