package org.example.controllers.restaurant;


import org.example.models.Restaurant;
import org.example.models.User;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.AddRestaurant;
import org.example.services.restaurant.AddReviewRestaurant;
import org.example.services.restaurant.RemoveRestaurant;
import org.example.services.restaurant.UpdateRestaurat;
import org.example.services.utils.IValidatorScanner;

public class RestaurantController {
    private static IValidatorScanner validatorScanner;

    public RestaurantController(IValidatorScanner validatorScanner) {
        RestaurantController.validatorScanner = validatorScanner;
    }

    public void addRestaurant() {
        ICommand command = new AddRestaurant(validatorScanner);
        command.execute();
    }

    public void removeRestaurant() {
        ICommand command = new RemoveRestaurant(validatorScanner);
        command.execute();
    }

    public void listRestaurants() {
        RestaurantRepository.getInstance().getAllRestaurants().values().forEach(restaurant -> {
            System.out.println(restaurant.toString());
            restaurant.getAverageRating();
        });
    }

    public void addReview() {
        ICommand command = new AddReviewRestaurant(validatorScanner);
        command.execute();
    }

    public void updateRestaurant() {
        ICommand command = new UpdateRestaurat(validatorScanner);
        command.execute();
    }

}
