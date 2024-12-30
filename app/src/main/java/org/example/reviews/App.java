/*
 * This source file was generated by the Gradle 'init' task
 */
package org.example.reviews;

import org.example.reviews.controllers.dish.CreateDishController;
import org.example.reviews.controllers.dish.DishController;
import org.example.reviews.controllers.dish.FindDishesController;
import org.example.reviews.controllers.dish.UpdateDishController;
import org.example.reviews.controllers.menu.CreateMenuController;
import org.example.reviews.controllers.menu.FindMenusController;
import org.example.reviews.controllers.menu.MenuController;
import org.example.reviews.controllers.restaurant.CreateRestaurantController;
import org.example.reviews.controllers.restaurant.FindRestaurantsController;
import org.example.reviews.controllers.restaurant.RemoveRestaurantController;
import org.example.reviews.controllers.restaurant.RestaurantController;
import org.example.reviews.controllers.restaurant.UpdateRestaurantController;
import org.example.reviews.services.dishes.CreateDishImpl;
import org.example.reviews.services.dishes.DishesService;
import org.example.reviews.services.dishes.FindDishesImpl;
import org.example.reviews.services.dishes.UpdateDishImpl;
import org.example.reviews.services.menus.CreateMenuImpl;
import org.example.reviews.services.menus.FindMenusImpl;
import org.example.reviews.services.menus.MenuService;
import org.example.reviews.services.restaurants.CreateRestaurantImpl;
import org.example.reviews.services.restaurants.FindRestaurantsImpl;
import org.example.reviews.services.restaurants.RemoveRestaurantImpl;
import org.example.reviews.services.restaurants.RestaurantService;
import org.example.reviews.services.restaurants.UpdateRestaurantImpl;
import org.example.reviews.utils.AppMenu;
import org.example.reviews.utils.ConsoleUtil;

public class App {

    public static void main(String[] args) {
        ConsoleUtil console  = new ConsoleUtil();

        RestaurantService restaurantService = new RestaurantService(
                new CreateRestaurantImpl(console),
                new FindRestaurantsImpl(),
                new UpdateRestaurantImpl(console),
                new RemoveRestaurantImpl(console));

        DishesService dishesService = new DishesService(
            new CreateDishImpl(console),
            new FindDishesImpl(console),
            new UpdateDishImpl(console)
        );

        MenuService menuService = new MenuService(new CreateMenuImpl(console), new FindMenusImpl());

        RestaurantController restaurantController =new RestaurantController(
                new CreateRestaurantController(restaurantService),
                new FindRestaurantsController(restaurantService),
                new UpdateRestaurantController(restaurantService),
                new RemoveRestaurantController(restaurantService),console);

        DishController dishController = new DishController(
                new CreateDishController(dishesService),
                new FindDishesController(dishesService),
                new UpdateDishController(dishesService),console);

        MenuController menuController = new MenuController(
                new CreateMenuController(menuService),
                new FindMenusController(menuService),
                dishController,
                console);

        AppMenu appMenu = new AppMenu(restaurantController, menuController, console);


        appMenu.execute();
    }
}
