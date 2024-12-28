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
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final DishRepository dishRepository = DishRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddReviewDish(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public Object execute() {
        repository.displayRestaurants();
        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar plato:");
        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
        menuRepository.showDishes(restaurant.getMenu());
        int optionDishes = validatorScanner.integerScanner("Selecciona el plato para review :");
        DishFood dishFood= restaurant.getMenu().getDishFoodList().get(optionDishes);
        User user = new User(validatorScanner.stringScanner("Ingrese su nombre"));
        String comment =validatorScanner.stringScanner("Ingrese su comentario");
        Float placeRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
        Float menuRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
        Float taste = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");

        return null;
    }
}
