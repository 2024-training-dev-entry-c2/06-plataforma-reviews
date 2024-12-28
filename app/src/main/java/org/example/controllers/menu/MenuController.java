package org.example.controllers.menu;

import org.example.models.User;
import org.example.services.Menu.AddDishFood;
import org.example.services.Menu.RemoveDishFood;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.AddReviewRestaurant;
import org.example.services.restaurant.RemoveRestaurant;

public class MenuController {

    public void addDishFood(String restaurantName, String dishName,String description, Double price){
        ICommand command = new AddDishFood(restaurantName,dishName,description,price);
        command.execute();
    }

    public void removeDishFood(String restaurantName, Integer indexDish){
        ICommand command = new RemoveDishFood(restaurantName,indexDish);
        command.execute();
    }

}
