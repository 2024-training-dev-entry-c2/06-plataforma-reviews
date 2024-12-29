package org.example.services.dishFood;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.models.User;
import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class AddReviewDish implements ICommand {
    private final DishRepository repository;
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final IValidatorScanner validatorScanner;

    public AddReviewDish(DishRepository repository, RestaurantRepository restaurantRepository, MenuRepository menuRepository, IValidatorScanner validatorScanner) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = null;
        DishFood dishFood= new DishFood();
        try {
            restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
            Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
            if (restaurant != null) {
                menuRepository.showDishes(restaurant.getMenu());
            }

            Integer optionDish = validatorScanner.integerScanner("seleccione un plato");
            if (restaurant!= null){
                 dishFood = restaurant.getMenu().getDishFoodList().get(optionDish);
            }else {
                 dishFood = new DishFood("null ", "nulll", 2000.0);
            }
            String comment = validatorScanner.stringScanner("Ingrese su comentario");
            Float tasteRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del sabor");
            Float presentationRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del presentacion");
            repository.addReview(dishFood, comment, tasteRating, presentationRating);
        } catch (NullPointerException e) {
            System.err.println("Error: Restaurant not found - " + restaurantName);
        }
    }
}