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



    public Float getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(Float menuRating) {
        this.menuRating = menuRating;
    }

    public Float getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(Float serviceRating) {
        this.serviceRating = serviceRating;
    }

    public Float getStoreRating() {
        return storeRating;
    }

    public void setStoreRating(Float storeRating) {
        this.storeRating = storeRating;
    }
}
