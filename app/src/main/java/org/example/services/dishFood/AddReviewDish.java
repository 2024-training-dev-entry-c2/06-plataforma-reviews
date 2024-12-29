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
    private final DishRepository repository = DishRepository.getInstance();
    private final RestaurantRepository restaurantRepository= RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddReviewDish(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String restaurantName = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        menuRepository.showDishes(restaurant.getMenu());
        Integer optionDish = validatorScanner.integerScanner("seleccione un plato");
        DishFood dishFood= restaurant.getMenu().getDishFoodList().get(optionDish);
        String comment = validatorScanner.stringScanner("Ingrese su comentario");
        Float tasteRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del sabor");
        Float presentationRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del presentacion");
        repository.addReview(dishFood, comment, tasteRating,presentationRating);
    }
}