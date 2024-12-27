package org.example.models;

public class ReviewFactory {
	public static Review createReview(String comment, Float... ratings) {
		Review review = switch (ratings.length) {
			case 3 -> new RestaurantReview(comment, ratings[0], ratings[1], ratings[2]);
			case 2 -> new DishReview(comment, ratings[0], ratings[1]);
			default -> null;
		};

		if (review != null) {
			review.calculateRating();
		}

		return review;
	}
}