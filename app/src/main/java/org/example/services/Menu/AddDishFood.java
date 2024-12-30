package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class AddDishFood implements ICommand {
    private final RestaurantRepository repository;
    private final MenuRepository menuRepository;
    private final IValidatorScanner validatorScanner;

    public AddDishFood(RestaurantRepository repository, MenuRepository menuRepository, IValidatorScanner validatorScanner) {
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
            String dishName = validatorScanner.stringScanner("Escribe el nombre del plato:");
            String description = validatorScanner.stringScanner("Escribe una descripcion:");
            Double price = validatorScanner.doubleScanner("Escribe el precio:");
            DishFood dishFood = new DishFood(dishName,description,price);
            System.out.println(dishFood.toString());
            menuRepository.addDishFood(restaurant,dishFood);

        } catch (NullPointerException e) {
            System.err.println("Error: Restaurant not found - " + restaurantName);
        }
    }
}


