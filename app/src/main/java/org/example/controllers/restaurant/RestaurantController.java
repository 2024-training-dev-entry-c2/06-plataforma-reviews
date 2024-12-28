package org.example.controllers.restaurant;

import org.example.models.DishFood;
import org.example.models.User;
import org.example.repositories.RestaurantRepository;
import org.example.services.Menu.AddDishFood;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.AddRestaurant;
import org.example.services.restaurant.AddReviewRestaurant;
import org.example.services.restaurant.RemoveRestaurant;

public class RestaurantController {
    public void addRestaurant(String name, String location) {
        ICommand command = new AddRestaurant(name, location);
        command.execute();
    }

    public void removeRestaurant(String name) {
        ICommand command = new RemoveRestaurant(name);
        command.execute();
    }

    public void listRestaurants() {
        RestaurantRepository.getInstance().getAllRestaurants().values().forEach(System.out::println);
    }
    public void addReview(String restaurantName, User user, String comment, Float rating){
        ICommand command = new AddReviewRestaurant(restaurantName,user,comment,rating);
        command.execute();
    }
    public void addDishFood(String restaurantName, String nameDish, String description, Double price){
        ICommand command = new AddDishFood(restaurantName,nameDish,description,price);
    }
}
