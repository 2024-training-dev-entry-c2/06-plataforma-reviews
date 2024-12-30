// src/main/java/restaurant/controller/review/ListReviewController.java
package restaurant.controller.review;

import restaurant.controller.interfaces.IController;
import restaurant.service.review.ListRestaurantReview;

public class ListReviewController implements IController {
    private final ListRestaurantReview command;

    public ListReviewController(ListRestaurantReview command) {
        this.command = command;
    }

    @Override
    public void execute() {
        command.execute();
    }
}