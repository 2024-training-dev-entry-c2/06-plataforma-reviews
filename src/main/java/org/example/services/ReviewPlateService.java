package org.example.services;

import org.example.models.Plate;
import org.example.models.ReviewFactory;
import org.example.models.ReviewFactory.ReviewType;
import org.example.models.ReviewPlate;

import java.util.List;

public class ReviewPlateService {
  public void addReview(Plate plate, Integer rating, String comment){
    ReviewPlate review = (ReviewPlate) ReviewFactory.createReview(ReviewType.PLATE, rating, comment);
    plate.addReview(review);
  }

  public List<ReviewPlate> getReviews(Plate plate){
    return plate.getReviews();
  }
}
