package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;


public class AddDishFood implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private String restaurantName;
    private String nameDish;
    private String description;
    private Double price;

    public AddDishFood(String restaurantName, String nameDish, String description, Double price) {
        this.restaurantName = restaurantName;
        this.nameDish = nameDish;
        this.description = description;
        this.price = price;
    }

    @Override
    public void execute() {
        try {
            Restaurant restaurant = repository.getRestaurant(restaurantName);
            DishFood dishFood = new DishFood(nameDish,description,price);
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