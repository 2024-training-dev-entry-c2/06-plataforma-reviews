package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

import java.util.Collections;


public class addDishFood implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public addDishFood(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    public void addDish(String menu, DishFood dishFood) {
        menuRepository.addDishFood(menu,dishFood);
    }

    @Override
    public void execute() {
//        repository.displayRestaurants();
//        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
//        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
//        String name = validatorScanner.stringScanner("Escribe el nombre del plato ");
//        String description = validatorScanner.stringScanner("Escribe una descripcion ");
//        double price = validatorScanner.integerScanner("Escribe el precio: ");
//        addDish(restaurant.getMenu().getName(), new DishFood(name, description, price, Collections.emptyList()));
//        return null;
    }
}
