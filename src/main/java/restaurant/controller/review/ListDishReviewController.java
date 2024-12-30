package restaurant.controller.review;

import restaurant.service.review.ListDishReview;
import restaurant.controller.interfaces.IController;

public class ListDishReviewController implements IController {
    private final ListDishReview listDishReview;

    public ListDishReviewController(ListDishReview listDishReview) {
        this.listDishReview = listDishReview;
    }

    @Override
    public void execute() {
        listDishReview.execute();
    }
}