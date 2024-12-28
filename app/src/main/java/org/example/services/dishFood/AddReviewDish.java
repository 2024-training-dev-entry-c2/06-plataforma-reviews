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

    private String restaurantName;
    private User user;
    private String comment;
    private Float[] rating;
    private Integer indexDishFood;

    public AddReviewDish(String restaurantName,Integer indexDishFood, User user, String comment,  Float... rating) {
        this.restaurantName = restaurantName;
        this.user = user;
        this.comment = comment;
        this.rating = rating;
        this.indexDishFood = indexDishFood;
    }

    @Override
    public void execute() {
        Restaurant restaurant = restaurantRepository.getRestaurant(restaurantName);
        DishFood dishFood= restaurant.getMenu().getDishFoodList().get(indexDishFood);
        repository.addReview(dishFood, comment, rating);
    }
}
//        repository.displayRestaurants();
//        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar plato:");
//        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
//        menuRepository.showDishes(restaurant.getMenu());
//        int optionDishes = validatorScanner.integerScanner("Selecciona el plato para review :");
//        DishFood dishFood= restaurant.getMenu().getDishFoodList().get(optionDishes);
//        User user = new User(validatorScanner.stringScanner("Ingrese su nombre"));
//        String comment =validatorScanner.stringScanner("Ingrese su comentario");
//        Float placeRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
//        Float menuRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
//        Float taste = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
