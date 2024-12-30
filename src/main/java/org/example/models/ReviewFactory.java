package org.example.models;

public class ReviewFactory {
  public enum ReviewType {
    RESTAURANT, PLATE
  }

  public static Review createReview(ReviewType type, Integer rating, String comment) {
    if (type == null) {
      throw new RuntimeException("Tipo de comentario no reconocido");
    }

    switch (type) {
      case RESTAURANT -> {
        return new ReviewRestaurant(rating, comment);
      }
      case PLATE -> {
        return new ReviewPlate(rating, comment);
      }
      default -> throw new RuntimeException("Tipo de comentario no reconocido");
    }
  }
}
