package org.nahulem.config;

import org.nahulem.controllers.menu.AddDishController;
import org.nahulem.controllers.menu.DeleteDishController;
import org.nahulem.controllers.menu.UpdateDishController;
import org.nahulem.controllers.restaurant.CreateRestaurantController;
import org.nahulem.controllers.restaurant.DeleteRestaurantController;
import org.nahulem.controllers.restaurant.ListRestaurantController;
import org.nahulem.controllers.restaurant.UpdateRestaurantController;
import org.nahulem.controllers.review.CreateDishReviewController;
import org.nahulem.controllers.review.CreateRestaurantReviewController;
import org.nahulem.controllers.review.ShowDishReviewController;
import org.nahulem.controllers.review.ShowRestaurantReviewController;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.services.menu.DeleteDishService;
import org.nahulem.services.menu.SelectDishService;
import org.nahulem.services.menu.UpdateDishService;
import org.nahulem.services.restaurant.CreateRestaurantService;
import org.nahulem.services.restaurant.DeleteRestaurantService;
import org.nahulem.services.restaurant.ListRestaurantService;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.services.restaurant.UpdateRestaurantService;
import org.nahulem.services.review.CreateDishReviewService;
import org.nahulem.services.review.CreateRestaurantReviewService;
import org.nahulem.services.review.ShowDishReviewService;
import org.nahulem.services.review.ShowRestaurantReviewService;
import org.nahulem.utils.MainMenu;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class AppConfig {
    public MainMenu createMainMenu() {
        DataRepository repository = DataRepository.getInstance();
        Validator validator = new Validator(new Scanner(System.in));

        ListRestaurantService listRestaurant = new ListRestaurantService(repository);
        SelectRestaurantService selectRestaurant = new SelectRestaurantService(repository, validator);
        AddDishService addDishService = new AddDishService(validator, repository, selectRestaurant);
        CreateRestaurantService createRestaurant = new CreateRestaurantService(addDishService, repository, validator);
        UpdateRestaurantService updateRestaurant = new UpdateRestaurantService(repository, selectRestaurant, validator);
        DeleteRestaurantService deleteRestaurant = new DeleteRestaurantService(repository, selectRestaurant);
        SelectDishService selectDish = new SelectDishService(validator, selectRestaurant);
        UpdateDishService updateDish = new UpdateDishService(repository, selectDish, validator);
        DeleteDishService deleteDish = new DeleteDishService(repository, selectDish);
        CreateDishReviewService createDishReview = new CreateDishReviewService(selectDish, validator);
        CreateRestaurantReviewService createRestaurantReview = new CreateRestaurantReviewService(selectRestaurant, validator);
        ShowDishReviewService showDishReview = new ShowDishReviewService(selectDish);
        ShowRestaurantReviewService showRestaurantReview = new ShowRestaurantReviewService(selectRestaurant);

        AddDishController addDishController = new AddDishController(addDishService);
        CreateRestaurantController createRestaurantController = new CreateRestaurantController(createRestaurant);
        UpdateRestaurantController updateRestaurantController = new UpdateRestaurantController(updateRestaurant);
        DeleteRestaurantController deleteRestaurantController = new DeleteRestaurantController(deleteRestaurant);
        ListRestaurantController listRestaurantController = new ListRestaurantController(listRestaurant);
        UpdateDishController updateDishController = new UpdateDishController(updateDish);
        DeleteDishController deleteDishController = new DeleteDishController(deleteDish);
        CreateDishReviewController createDishReviewController = new CreateDishReviewController(createDishReview);
        ShowDishReviewController showDishReviewController = new ShowDishReviewController(showDishReview);
        CreateRestaurantReviewController createRestaurantReviewController = new CreateRestaurantReviewController(createRestaurantReview);
        ShowRestaurantReviewController showRestaurantReviewController = new ShowRestaurantReviewController(showRestaurantReview);

        return new MainMenu(
                addDishController,
                validator,
                createRestaurantController,
                updateRestaurantController,
                deleteRestaurantController,
                listRestaurantController,
                updateDishController,
                deleteDishController,
                createDishReviewController,
                showDishReviewController,
                createRestaurantReviewController,
                showRestaurantReviewController
        );
    }
}
