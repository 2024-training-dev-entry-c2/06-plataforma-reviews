package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.models.User;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class AddReviewRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private String restaurantName;
    private User user;
    private String comment;
    private Float [] rating ;

    public AddReviewRestaurant(String restaurantName, User user, String comment, Float... rating) {
        this.restaurantName = restaurantName;
        this.user = user;
        this.comment = comment;
        this.rating = rating;
    }

    @Override
    public void execute() {
            try {
                repository.addReview(restaurantName, user, comment, rating);
                System.out.println("Review added successfully to: " + restaurantName);
            } catch (NullPointerException e) {
                System.err.println("Error: Restaurant not found - " + restaurantName);
            } catch (Exception e) {
                System.err.println("Error while adding review: " + e.getMessage());
            }
        }

}



//        repository.displayRestaurants();
//        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
//        String nameRestaurant = repository.getRestaurantNameByIndex(optionRestaurant);
//        User user = new User(validatorScanner.stringScanner("Ingrese su nombre"));
//        String comment =validatorScanner.stringScanner("Ingrese su comentario");
//        Float placeRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
//        Float menuRating = validatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu");
//        addReview(nameRestaurant,user,comment,placeRating,menuRating);