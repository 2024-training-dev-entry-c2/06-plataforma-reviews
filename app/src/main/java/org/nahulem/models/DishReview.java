package org.nahulem.models;

public class DishReview extends Review {
    private Float tasteRating;
    private Float presentationRating;

    public DishReview(String comment, Float tasteRating, Float presentationRating) {
        super(comment);
        this.tasteRating = tasteRating;
        this.presentationRating = presentationRating;
        calculateRating();
    }

    @Override
    public void calculateRating() {
        setAverageRating((tasteRating + presentationRating) / 2);
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n      Sabor: " + tasteRating +
                "\n      Presentación: " + presentationRating;
    }


    public Float getTasteRating() {
        return tasteRating;
    }
}
