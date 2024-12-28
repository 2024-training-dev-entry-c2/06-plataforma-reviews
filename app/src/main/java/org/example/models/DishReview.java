package org.example.models;

public class DishReview extends Review {
    private Float tasteRating;
    private Float presentationRating;

    public DishReview(String idReview, String comment, Float tasteRating, Float presentationRating) {
        super(idReview, comment);
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
        calculateRating();
    }

    @Override
    public void calculateRating() {
        setAverageRating((tasteRating + presentationRating) / 2);
    }

    public Float getTasteRating() {
        return tasteRating;
    }
}