package org.nahulem.services.review;

import org.nahulem.models.Dish;
import org.nahulem.models.DishReview;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.menu.SelectDishService;

import java.util.List;

public class ShowDishReviewService implements ICommand<List<DishReview>> {
    private final SelectDishService selectDishService;

    public ShowDishReviewService(SelectDishService selectDishService) {
        this.selectDishService = selectDishService;
    }


    @Override
    public List<DishReview> execute() {
        Dish dish = selectDishService.execute();

        if (dish == null) {
            return List.of();
        }

        return dish.getReviews().stream().map(DishReview.class::cast).toList();
    }
}
