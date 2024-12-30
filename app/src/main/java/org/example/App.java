package org.example;

import org.example.command.*;
import org.example.command.menu.AddPlateCommand;
import org.example.command.menu.DeletePlateCommand;
import org.example.command.menu.EditMenuCommand;
import org.example.command.menu.GetMenuCommand;
import org.example.command.restaurant.AddRestaurantCommand;
import org.example.command.restaurant.DeleteRestaurantCommand;
import org.example.command.restaurant.EditRestaurantCommand;
import org.example.command.restaurant.GetRestaurantsCommand;
import org.example.command.review.AddPlateReviewCommand;
import org.example.command.review.AddRestaurantReviewCommand;
import org.example.command.review.GetReviewsPlate;
import org.example.command.review.GetReviewsRestaurant;
import org.example.command.utils.ConsoleHandler;
import org.example.command.utils.IHandler;
import org.example.controllers.MenuController;
import org.example.controllers.RestaurantController;
import org.example.controllers.ReviewController;

public class App {

    public static void main(String[] args) {

        RestaurantController restaurantController = new RestaurantController();
        MenuController menuController = new MenuController();
        ReviewController reviewController = new ReviewController();
        IHandler handler = new ConsoleHandler();

        UserMenu menu = new UserMenu(handler);
        menu.addCommand(1, new AddRestaurantCommand(restaurantController, handler));
        menu.addCommand(2, new EditRestaurantCommand(restaurantController));
        menu.addCommand(3, new DeleteRestaurantCommand(restaurantController));
        menu.addCommand(4, new GetRestaurantsCommand(restaurantController));
        menu.addCommand(5, new AddPlateCommand(menuController));
        menu.addCommand(6, new EditMenuCommand(menuController));
        menu.addCommand(7, new DeletePlateCommand(menuController));
        menu.addCommand(8, new GetMenuCommand(menuController));
        menu.addCommand(9, new AddRestaurantReviewCommand(reviewController));
        menu.addCommand(10, new AddPlateReviewCommand(reviewController));
        menu.addCommand(11, new GetReviewsRestaurant(reviewController));
        menu.addCommand(12, new GetReviewsPlate(reviewController));

        System.out.println("******  Bienvenido  *****");
        menu.showMenu();
    }
}
