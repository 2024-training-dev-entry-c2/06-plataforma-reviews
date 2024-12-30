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
        Float average = (tasteRating + presentationRating) / 2;
        setAverageRating(Math.round(average * 100) / 100.0f);
    }



    @Override
    public String toString() {
        return super.toString() +
                "\n      Sabor: " + tasteRating +
                "\n      Presentación: " + presentationRating;
    }

    public float getTasteRating() {
        return tasteRating;
    }

    public float getPresentationRating() {
        return presentationRating;
    }
}
