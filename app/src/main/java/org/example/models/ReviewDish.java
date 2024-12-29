package org.example.models;

public class ReviewDish implements IReview {
    private Float tasteRating;
    private Float presentationRating;
    private String comment;


    public ReviewDish(String comment, Float tasteRating, Float presentationRating) {
        this.comment=comment;
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
    }
    @Override
    public void showDetails() {

    }

    public Float getTasteRating() {
        return tasteRating;
    }

    public void setTasteRating(Float tasteRating) {
        this.tasteRating = tasteRating;
    }

    public Float getPresentationRating() {
        return presentationRating;
    }

    public void setPresentationRating(Float presentationRating) {
        this.presentationRating = presentationRating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
