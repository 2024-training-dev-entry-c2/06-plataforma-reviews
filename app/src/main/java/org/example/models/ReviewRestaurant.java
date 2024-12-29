package org.example.models;

public class ReviewRestaurant implements IReview {

    private Float serviceRating;
    private Float placeRating;
    private Float menuRating;
    private String comment;

    public ReviewRestaurant(String comment, Float serviceRating, Float placeRating, Float menuRating) {
        this.comment=comment;
        this.serviceRating = serviceRating;
        this.placeRating = placeRating;
        this.menuRating = menuRating;
    }

    @Override
    public void showDetails() {

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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
