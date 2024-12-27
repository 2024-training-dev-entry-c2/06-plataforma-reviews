package org.example.models;

public class DishReview extends Review {
    private Float tasteRating;
    private Float presentationRating;

    public DishReview(String id, Float tasteRating, Float presentationRating, String comment) {
        super(id, (tasteRating + presentationRating) / 2, comment);
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
    }
}