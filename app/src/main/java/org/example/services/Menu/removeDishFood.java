package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

import java.util.Collections;


public class removeDishFood implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private String restaurantName;
    private Integer indexDishFood;

    public removeDishFood(String restaurantName, int dishFood) {
        this.restaurantName = restaurantName;
        this.indexDishFood = dishFood;
    }

    @Override
    public void execute() {
        try {
            Restaurant restaurant = repository.getRestaurant(restaurantName);
            menuRepository.removeDishFood(restaurant,indexDishFood);
            System.out.println("Review added successfully to: " + restaurantName);
        } catch (NullPointerException e) {
            System.err.println("Error: Restaurant not found - " + restaurantName);
        }
    }

}


//        repository.displayRestaurants();
//        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
//        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
//        menuRepository.showDishes(restaurant.getMenu());
//        int optionDishes = validatorScanner.integerScanner("Selecciona el plato para borrar :");
//        DishFood dishFood= restaurant.getMenu().getDishFoodList().get(optionDishes);
//        removeDish( restaurant.getMenu(),dishFood);
//        return null;
