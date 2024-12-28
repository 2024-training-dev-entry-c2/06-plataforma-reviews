package org.example.models;

public class ReviewDish extends Review {
    private Float tasteRating;
    private Float presentationRating;

    public ReviewDish(String comment) {
        super(comment);
    }

    public ReviewDish(String comment, Float tasteRating, Float presentationRating) {
        super(comment);
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
    }

    @Override
    void showDetails() {

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
}
