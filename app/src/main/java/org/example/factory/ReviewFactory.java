package org.example.factory;

import constants.TypeReview;
import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.review.DishReview;
import org.example.models.review.RestaurantReview;
import org.example.models.review.Review;

public class ReviewFactory {
  public static Review createReview(TypeReview type, String comment, Float rating, Object entity, Float taste, Float presentation) {
    if (type.equals(TypeReview.DISH) && entity instanceof Dish) {
      return new DishReview(comment, (Dish) entity, taste, presentation);
    } else if (type.equals(TypeReview.RESTAURANT) && entity instanceof Restaurant) {
      return new RestaurantReview(comment, rating, (Restaurant) entity);
    } else {
      System.err.println("Invalid review type");
    }
    return null;
  }

}
