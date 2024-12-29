package org.example.controllers.dishFood;

import org.example.controllers.menu.MenuController;
import org.example.models.User;

import org.example.services.dishFood.AddReviewDish;
import org.example.services.dishFood.ShowReviews;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class DishFoodController {


    public static IValidatorScanner validatorScanner;

    public DishFoodController(IValidatorScanner validatorScanner) {
        DishFoodController.validatorScanner = validatorScanner;
    }

    public void addReview(){
        ICommand command = new AddReviewDish(validatorScanner);
        command.execute();
    }
    public void showReviews(){
        ICommand command = new ShowReviews(validatorScanner);
        command.execute();
    }

}
