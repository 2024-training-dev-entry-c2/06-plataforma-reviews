package org.nahulem.models;

public class RestaurantReview extends Review {
    private Float serviceRating;
    private Float menuRating;
    private Float storeRating;

    public RestaurantReview(String comment, Float menuRating, Float serviceRating, Float storeRating) {
        super(comment);
        this.menuRating = menuRating;
        this.serviceRating = serviceRating;
        this.storeRating = storeRating;
        calculateRating();
    }

    @Override
    public void calculateRating() {
        setAverageRating((menuRating + serviceRating + storeRating) / 3);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n      Menú: " + menuRating +
                "\n      Servicio: " + serviceRating +
                "\n      Instalaciones: " + storeRating;
    }
}
