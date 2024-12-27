package org.example.models;

public class RestaurantReview extends Review {
    private Float serviceRating;
    private Float menuRating;
    private Float storeRating;

    public RestaurantReview(String id, Float serviceRating, Float menuRating, Float storeRating, String comment) {
        super(id, (serviceRating + menuRating + storeRating) / 3, comment);
        this.serviceRating = serviceRating;
        this.menuRating = menuRating;
        this.storeRating = storeRating;
    }

}