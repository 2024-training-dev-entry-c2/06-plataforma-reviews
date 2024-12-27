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
    System.out.println("\n----------- Reseña -----------");
    System.out.println("Restaurante: " + restaurant.getName());
    System.out.println("Calificación: " + getRating());
    System.out.println("Comentario: " + getComment());
    System.out.println("------------------------------");
  }

  public Restaurant getRestaurant() {
    return restaurant;
  }

  public void setRestaurant(Restaurant restaurant) {
    this.restaurant = restaurant;
  }
}
