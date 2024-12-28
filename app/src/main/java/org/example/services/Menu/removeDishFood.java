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
    private final IValidatorScanner validatorScanner;

    public removeDishFood(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    public void removeDish(Menu menu, DishFood dishFood) {

        menuRepository.removeDishFood(menu,dishFood);
    }

    @Override
    public Object execute() {//String name, String description, Double price, List<Review> reviewList)
        repository.displayRestaurants();
        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
        menuRepository.showDishes(restaurant.getMenu());
        int optionDishes = validatorScanner.integerScanner("Selecciona el plato para borrar :");
        DishFood dishFood= restaurant.getMenu().getDishFoodList().get(optionDishes);
        removeDish( restaurant.getMenu(),dishFood);
        return null;
    }
}
