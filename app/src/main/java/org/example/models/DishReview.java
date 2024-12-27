package org.example.models;

public class DishReview extends Review {
  private Float tasteRating;
  private Float presentationRating;

  public DishReview() {
  }

  public DishReview(Integer reviewId, Float ratingAverage, String comment, Float tasteRating, Float presentationRating) {
    super(reviewId, ratingAverage, comment);
    this.tasteRating = tasteRating;
    this.presentationRating = presentationRating;
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

  @Override
  public void calculateRating() {
    setRatingAverage((tasteRating + presentationRating) / 2);
  }
}