package org.example.controllers;

import org.example.models.Plate;
import org.example.services.ReviewPlateService;

public class ReviewPlateController {
  private ReviewPlateService reviewPlateService;

  public ReviewPlateController(){
    this.reviewPlateService = new ReviewPlateService();
  }

  public void addReview(Plate plate, Integer rating, String comment){
    reviewPlateService.addReview(plate, rating, comment);
    System.out.println("Reseña agregada exitosamente.");
  }

  public void getReviews(Plate plate){
    System.out.println("Reseñas de este plato: " + plate.getName());
    reviewPlateService.getReviews(plate).forEach(System.out::println);
  }

  public void setReviewPlateService(ReviewPlateService reviewPlateService) {
    this.reviewPlateService = reviewPlateService;
  }
}
