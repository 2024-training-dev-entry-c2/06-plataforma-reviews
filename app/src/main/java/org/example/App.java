package org.example;

import org.example.controllers.menu.AddDishController;
import org.example.controllers.menu.DeleteDishController;
import org.example.controllers.menu.UpdateDishController;
import org.example.controllers.restaurant.CreateRestaurantController;
import org.example.controllers.restaurant.DeleteRestaurantController;
import org.example.controllers.restaurant.ListRestaurantController;
import org.example.controllers.restaurant.UpdateRestaurantController;
import org.example.controllers.review.CreateDishReviewController;
import org.example.controllers.review.CreateRestaurantReviewController;
import org.example.controllers.review.ListDishReviewController;
import org.example.controllers.review.ListRestaurantReviewController;
import org.example.repositories.RestaurantRepository;
import org.example.services.menu.AddDish;
import org.example.services.menu.DeleteDish;
import org.example.services.menu.SelectDish;
import org.example.services.menu.UpdateDish;
import org.example.services.restaurant.AddRestaurantObserver;
import org.example.services.restaurant.CreateRestaurant;
import org.example.services.restaurant.DeleteRestaurant;
import org.example.services.restaurant.ListRestaurant;
import org.example.services.restaurant.SelectRestaurant;
import org.example.services.restaurant.UpdateRestaurant;
import org.example.services.review.CreateDishReview;
import org.example.services.review.CreateRestaurantReview;
import org.example.services.review.ListDishReview;
import org.example.services.review.ListRestaurantReview;
import org.example.utils.MainMenu;
import org.example.utils.Validator;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Validator validator = new Validator(new Scanner(System.in));

        // region Command Controllers
        RestaurantRepository restaurantRepository = RestaurantRepository.getInstance();

        SelectRestaurant selectRestaurant = new SelectRestaurant(validator, restaurantRepository);
        AddDish addDish = new AddDish(validator, selectRestaurant);
        CreateRestaurant createRestaurant = new CreateRestaurant(validator, addDish, restaurantRepository);

        UpdateRestaurant updateRestaurant = new UpdateRestaurant(validator, restaurantRepository, selectRestaurant);
        DeleteRestaurant deleteRestaurant = new DeleteRestaurant(restaurantRepository, selectRestaurant);
        ListRestaurant listRestaurant = new ListRestaurant(restaurantRepository);

        SelectDish selectDish = new SelectDish(validator, selectRestaurant);
        UpdateDish updateDish = new UpdateDish(validator, selectDish);
        DeleteDish deleteDish = new DeleteDish(selectDish, restaurantRepository);

        CreateDishReview createDishReview = new CreateDishReview(validator, selectDish);
        AddRestaurantObserver addRestaurantObserver = new AddRestaurantObserver(validator);
        CreateRestaurantReview createRestaurantReview = new CreateRestaurantReview(validator, selectRestaurant, addRestaurantObserver);

        ListDishReview listDishReview = new ListDishReview(selectDish);
        ListRestaurantReview listRestaurantReview = new ListRestaurantReview(selectRestaurant);

        CreateRestaurantController createRestaurantController = new CreateRestaurantController(createRestaurant);
        UpdateRestaurantController updateRestaurantController = new UpdateRestaurantController(updateRestaurant);
        DeleteRestaurantController deleteRestaurantController = new DeleteRestaurantController(deleteRestaurant);
        ListRestaurantController listRestaurantController = new ListRestaurantController(listRestaurant);
        AddDishController addDishController = new AddDishController(addDish);
        UpdateDishController updateDishController = new UpdateDishController(updateDish);
        DeleteDishController deleteDishController = new DeleteDishController(deleteDish);
        CreateDishReviewController createDishReviewController = new CreateDishReviewController(createDishReview);
        ListDishReviewController listDishReviewController = new ListDishReviewController(listDishReview);
        CreateRestaurantReviewController createRestaurantReviewController = new CreateRestaurantReviewController(createRestaurantReview);
        ListRestaurantReviewController listRestaurantReviewController = new ListRestaurantReviewController(listRestaurantReview);

        // endregion

        MainMenu menu = new MainMenu(validator, createRestaurantController, updateRestaurantController, deleteRestaurantController, listRestaurantController, addDishController, updateDishController, deleteDishController, createDishReviewController, listDishReviewController, createRestaurantReviewController, listRestaurantReviewController);
        menu.execute();
    }
}