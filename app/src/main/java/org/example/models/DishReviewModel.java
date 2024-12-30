package org.example.models;

public class DishReviewModel extends Review {
  private DishModel dish;

  public DishReviewModel(String reviewerName, Double rating, String comment, DishModel dish) {
    super(reviewerName, rating, comment);
    this.dish = dish;
  }

  public DishModel getDish() {
    return dish;
  }

  public void setDish(DishModel dish) {
    this.dish = dish;
  }

  @Override
  public String getReviewType() {
    return "Dish";
  }
}
