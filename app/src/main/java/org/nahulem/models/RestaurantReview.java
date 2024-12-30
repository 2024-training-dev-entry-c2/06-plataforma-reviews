package org.nahulem.models;

public class RestaurantReview extends Review {
    private Float serviceRating;
    private Float menuRating;
    private Float environmentRating;

    public RestaurantReview(String comment, Float menuRating, Float serviceRating, Float environmentRating) {
        super(comment);
        this.menuRating = menuRating;
        this.serviceRating = serviceRating;
        this.environmentRating = environmentRating;
        calculateRating();
    }

    @Override
    public void calculateRating() {
        setAverageRating((menuRating + serviceRating + environmentRating) / 3);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n      Menú: " + menuRating +
                "\n      Servicio: " + serviceRating +
                "\n      Instalaciones: " + environmentRating;
    }

    public Float getMenuRating() {
        return menuRating;
    }

    public Float getServiceRating() {
        return serviceRating;
    }

    public Float getEnvironmentRating() {
        return environmentRating;
    }
}
