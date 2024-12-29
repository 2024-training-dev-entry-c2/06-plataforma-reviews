package org.example.controllers.dishFood;

import org.example.models.User;

import org.example.services.dishFood.AddReviewDish;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;


public class DishFoodController {



    public void addReview(String restaurantName, Integer indexDish , User user, String comment, Float ...rating){
        ICommand command = new AddReviewDish(restaurantName,indexDish,user,comment,rating);
        command.execute();
    }

}
