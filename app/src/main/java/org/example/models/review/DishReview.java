package org.example.models.review;

import org.example.models.Dish;

public class DishReview extends Review {
  private Dish dish;

  public DishReview(String comment, Float rating, Dish dish) {
    super(comment, rating);
    this.dish = dish;
  }

  public DishReview() {
  }

  @Override
  public void displayReview() {
    System.out.println("\n----------- Review -----------");
    System.out.println("Dish: " + dish.getName());
    System.out.println("Rating: " + getRating());
    System.out.println("Comment: " + getComment());
    System.out.println("------------------------------");
  }

  public Dish getDish() {
    return dish;
  }

  public void setDish(Dish dish) {
    this.dish = dish;
  }
}
