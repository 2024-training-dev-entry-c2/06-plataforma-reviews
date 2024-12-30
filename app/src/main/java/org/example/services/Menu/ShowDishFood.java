package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class ShowDishFood implements ICommand {
    private final RestaurantRepository repository;
    private final MenuRepository menuRepository;
    private final IValidatorScanner validatorScanner;

    public ShowDishFood(RestaurantRepository repository, MenuRepository menuRepository, IValidatorScanner validatorScanner) {
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
            menuRepository.showDishes(restaurant.getMenu());
        } catch (NullPointerException e) {
            System.err.println("Error: Restaurant not found - " + restaurantName);
        }
    }
}


//        repository.displayRestaurants();
//        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
//        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
//        String name = validatorScanner.stringScanner("Escribe el nombre del plato ");
//        String description = validatorScanner.stringScanner("Escribe una descripcion ");
//        double price = validatorScanner.integerScanner("Escribe el precio: ");
//        addDish(restaurant.getMenu().getName(), new DishFood(name, description, price, Collections.emptyList()));
//        return null;