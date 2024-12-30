package org.example.services;

import org.example.models.Review;
import org.example.repositories.ReviewNotifier;
import org.example.repositories.ReviewRepository;

import java.util.List;

public class ReviewService {
  private final ReviewRepository reviewRepository;

  public ReviewService(ReviewRepository reviewRepository) {
    this.reviewRepository = reviewRepository ;
    this.reviewRepository.addObserver(new ReviewNotifier());
  }

  public void createRestaurantReview(Float score, String comment, Long restaurantId) {
   if(this.reviewRepository.getRestaurant(restaurantId) != null) {
     this.reviewRepository.createReview("Restaurant", score, comment, restaurantId);
     this.reviewRepository.notifyObservers("Restaurant", restaurantId);
   }
   else {
     System.out.println("El restaurante con ID " + restaurantId + " no existe.");
   }
  }

  public void createDishReview(Float score, String comment, Long dishId) {
    if(this.reviewRepository.getDish(dishId) != null) {
      this.reviewRepository.createReview("Dish", score, comment, dishId);
      this.reviewRepository.notifyObservers("Dish", dishId);
    }
    else {
      System.out.println("El plato con ID " + dishId + " no existe.");
    }
  }

  public void displayRestaurants() {
    System.out.println("\nRestaurantes disponibles:");
    this.reviewRepository.displayRestaurants();
  }

  public void displayMenu( Long restaurantId) {
    if(this.reviewRepository.getRestaurant(restaurantId) != null) {
      System.out.println("\nPlatos disponibles:");
      this.reviewRepository.getDishes(restaurantId);
    }
    else {
      System.out.println("El restaurante con ID " + restaurantId + " no existe.");
    }
  }


  public void displayReviews(String typeOfReview,Long reviewedId) {
    List<Review> reviews = this.reviewRepository.getReviewsById(typeOfReview, reviewedId);
    if(reviews != null) {
      System.out.println("\nLista de reseñas: " + this.reviewRepository.getReviewList(reviews));
      System.out.println("\nLa calificación promedio es " + this.reviewRepository.getAverageScore(reviews)+"\n");
    }
    else {
      System.out.println("El  ID  no existe.");
    }
  }


}
