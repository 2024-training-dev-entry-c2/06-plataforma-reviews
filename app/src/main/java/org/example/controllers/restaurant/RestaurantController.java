package org.example.controllers.restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.*;
import org.example.services.utils.IValidatorScanner;
import org.example.services.utils.ValidatorScanner;

public class RestaurantController {
    private final IValidatorScanner validatorScanner;
    private final RestaurantRepository restaurantRepository;

    public RestaurantController(IValidatorScanner validatorScanner, RestaurantRepository restaurantRepository) {
        this.validatorScanner = validatorScanner;
        this.restaurantRepository = restaurantRepository;
    }

    public void addRestaurant() {
        ICommand command = new AddRestaurant(restaurantRepository,validatorScanner);
        command.execute();
    }

    public void removeRestaurant() {
        ICommand command = new RemoveRestaurant(restaurantRepository,validatorScanner);
        command.execute();
    }

    public void listRestaurants() {
        RestaurantRepository.getInstance().getAllRestaurants().values().forEach(restaurant -> { System.out.println(restaurant.toString());restaurant.getAverageRating(); });
    }

    public void addReview() {
        ICommand command = new AddReviewRestaurant(restaurantRepository,validatorScanner);
        command.execute();
    }
    public void showReview() {
        ICommand command = new ShowReviewRestaurant(restaurantRepository,validatorScanner);
        command.execute();
    }


}
