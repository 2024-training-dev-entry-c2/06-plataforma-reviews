package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class AddDishFood implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddDishFood(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;
        try {
            restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
            Restaurant restaurant = repository.getRestaurant(restaurantName);
            String dishName = validatorScanner.stringScanner("Escribe el nombre del plato ");
            String description = validatorScanner.stringScanner("Escribe una descripcion ");
            Double price = validatorScanner.doubleScanner("Escribe el precio: ");
            DishFood dishFood = new DishFood(dishName,description,price);
            System.out.println(dishFood.toString());
            menuRepository.addDishFood(restaurant,dishFood);
            System.out.println("Review added successfully to: " + restaurantName);
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