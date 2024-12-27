package org.example.models.review;

import org.example.models.Restaurant;

public class RestaurantReview extends Review{
  private Restaurant restaurant;

  public RestaurantReview(String comment, Float rating, Restaurant restaurant) {
    super(comment, rating);
    this.restaurant = restaurant;
  }

  public RestaurantReview() {
  }

  @Override
  public void displayReview() {
    System.out.println("\n----------- Review -----------");
    System.out.println("Restaurant: " + restaurant.getName());
    System.out.println("Rating: " + getRating());
    System.out.println("Comment: " + getComment());
    System.out.println("------------------------------");
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }
}
