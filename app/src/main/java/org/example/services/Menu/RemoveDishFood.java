package org.example.services.Menu;

import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class RemoveDishFood implements ICommand {
    private final RestaurantRepository repository;
    private final MenuRepository menuRepository;
    private final IValidatorScanner validatorScanner;

    public RemoveDishFood(RestaurantRepository repository, MenuRepository menuRepository, IValidatorScanner validatorScanner) {
        this.repository = repository;
        this.menuRepository = menuRepository;
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;
        try {
            restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
            Restaurant restaurant = repository.getRestaurant(restaurantName);
            int indexDishFood = validatorScanner.integerScanner("Escribe el numero del plato");
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
