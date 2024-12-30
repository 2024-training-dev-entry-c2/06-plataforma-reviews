package restaurant.models;

import restaurant.Interface.IReview;

public class DishReview implements IReview {
    private final String comment;
    private final Double qualification;
    private final Dish dish;

    public DishReview(String comment, Double qualification, Dish dish) {
        this.comment = comment;
        this.qualification = qualification;
        this.dish = dish;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public Double getQualification() {
        return qualification;
    }

    public Dish getDish() {
        return dish;
    }
}