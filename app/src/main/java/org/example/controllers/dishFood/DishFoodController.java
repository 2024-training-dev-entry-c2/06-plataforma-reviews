package org.example.controllers.dishFood;

import org.example.controllers.menu.MenuController;
import org.example.models.User;

import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.dishFood.AddReviewDish;
import org.example.services.dishFood.ShowReviews;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class DishFoodController {
    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    public final IValidatorScanner validatorScanner;

    public DishFoodController(RestaurantRepository restaurantRepository, MenuRepository menuRepository, DishRepository dishRepository, IValidatorScanner validatorScanner) {
        this.restaurantRepository = restaurantRepository;
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
        this.validatorScanner = validatorScanner;
    }

    public void addReview(){
        ICommand command = new AddReviewDish(dishRepository,restaurantRepository,menuRepository,validatorScanner);
        command.execute();
    }
    public void showReviews(){
        ICommand command = new ShowReviews(validatorScanner);
        command.execute();
    }

}
