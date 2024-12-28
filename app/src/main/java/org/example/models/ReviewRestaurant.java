package org.example.models;

public class ReviewRestaurant extends Review {

    private Float serviceRating;
    private Float placeRating;
    private Float menuRating;

    public ReviewRestaurant(String comment) {
        super(comment);
    }

    public ReviewRestaurant(String comment, Float serviceRating, Float placeRating, Float menuRating) {
        super(comment);
        this.serviceRating = serviceRating;
        this.placeRating = placeRating;
        this.menuRating = menuRating;
    }

    @Override
    void showDetails() {

    }

    public Float getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(Float serviceRating) {
        this.serviceRating = serviceRating;
    }

    public Float getPlaceRating() {
        return placeRating;
    }

    public void setPlaceRating(Float placeRating) {
        this.placeRating = placeRating;
    }

    public Float getMenuRating() {
        return menuRating;
    }

    public void setMenuRating(Float menuRating) {
        this.menuRating = menuRating;
    }
}
