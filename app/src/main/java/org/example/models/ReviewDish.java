package org.example.models;

public class ReviewDish implements IReview {
    private Double tasteRating;
    private Double presentationRating;
    private String comment;


    public ReviewDish(String comment, Double tasteRating, Double presentationRating) {
        this.comment=comment;
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
    }
    @Override
    public void showDetails() {

    }

    public Double getTasteRating() {
        return tasteRating;
    }

    public void setTasteRating(Double tasteRating) {
        this.tasteRating = tasteRating;
    }

    public Double getPresentationRating() {
        return presentationRating;
    }

    public void setPresentationRating(Double presentationRating) {
        this.presentationRating = presentationRating;
    }


}
