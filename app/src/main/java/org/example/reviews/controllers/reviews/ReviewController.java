package org.example.reviews.controllers.reviews;

import org.example.reviews.controllers.interfaces.IController;

import org.example.reviews.services.reviews.ReviewService;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

public class ReviewController implements IController {
    private CreateRestaurantReviewController createRestaurantReviewController;
    private CreateDishReviewController createDishReviewController;
    private FindAllRestaurantReviewsController findAllRestaurantReviewsController;
    private FindAllDishesReviewsController findAllDishesReviewsController;
    private ConsoleUtil console;

    public ReviewController(ReviewService reviewService, ConsoleUtil console) {
        this.console = console;
        this.createRestaurantReviewController = new CreateRestaurantReviewController(reviewService);
        this.createDishReviewController = new CreateDishReviewController(reviewService);
        this.findAllRestaurantReviewsController = new FindAllRestaurantReviewsController(reviewService);
        this.findAllDishesReviewsController = new FindAllDishesReviewsController(reviewService);
    }
    @Override
    public void execute() {
        Map<Integer, IController> controllers = Map.of(
                1, createRestaurantReviewController,
                2, createDishReviewController,
                3, findAllRestaurantReviewsController,
                4, findAllDishesReviewsController

        );
        int option = 0;
        do {
            showReviewsMenu();
            option = console.readInt("Seleccione una opcion: ");
            executeController(option, controllers);
        }while (option != 9);

        console.writeLine("Volviendo al menu anterior...");

    }

    private void executeController(int option, Map<Integer, IController> controllers) {
        if (option != 9) {
            controllers.get(option).execute();
        }
    }

    public void showReviewsMenu(){
        console.writeLine("1. Crear comentario de restaurante");
        console.writeLine("2. Crear comentario de plato");
        console.writeLine("3. Listar todos las comentarios de un restaurante");
        console.writeLine("4. Listar todos las comentarios de un plato");
        console.writeLine("9. Volver menu anterior");
    }
}
