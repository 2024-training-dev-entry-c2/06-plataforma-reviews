package org.nahulem.services.review;

import org.nahulem.factories.ReviewFactory;
import org.nahulem.models.Dish;
import org.nahulem.models.Review;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.menu.SelectDishService;
import org.nahulem.utils.Validator;

public class CreateDishReviewService implements ICommand<Boolean> {
    private final SelectDishService selectDishService;
    private final Validator validator;

    public CreateDishReviewService(SelectDishService selectDishService, Validator validator) {
        this.selectDishService = selectDishService;
        this.validator = validator;
    }

    @Override
    public Boolean execute() {
        Dish dish = selectDishService.execute();

        if (dish == null) {
            return false;
        }

        Review review = createDishReview();

        dish.addReview(review);
        return true;
    }

    private Review createDishReview() {
        String comment = validator.readString("Ingrese un comentario para el plato: ");
        Float tasteRating = validator.readRating("Ingrese una calificación para el sabor (0-5): ");
        Float presentationRating = validator.readRating("Ingrese una calificación para la presentación (0-5): ");

        return ReviewFactory.createReview(comment, tasteRating, presentationRating);
    }
}
