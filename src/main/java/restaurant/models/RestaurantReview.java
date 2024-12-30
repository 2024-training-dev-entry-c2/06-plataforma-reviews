package restaurant.models;

import restaurant.Interface.IReview;

public class RestaurantReview implements IReview {
    private final String comment;
    private final Double qualification;
    private final Restaurant restaurant;

    public RestaurantReview(String comment, Double qualification, Restaurant restaurant) {
        this.comment = comment;
        this.qualification = qualification;
        this.restaurant = restaurant;
    }

    @Override
    public String getComment() {
        return comment;
    }

    @Override
    public Double getQualification() {
        return qualification;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}