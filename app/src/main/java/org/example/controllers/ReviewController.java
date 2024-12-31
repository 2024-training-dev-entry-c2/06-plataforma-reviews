package org.example.controllers;

import org.example.factories.DishReviewFactory;
import org.example.factories.RestaurantReviewFactory;
import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.services.review.AddReviewService;
import org.example.services.review.RemoveReviewService;
import org.example.utils.consoleUtils.ConsoleUtils;

public class ReviewController {
    private final AddReviewService addReviewService;
    private final RemoveReviewService removeReviewService;
    private final ConsoleUtils console;

    public ReviewController(AddReviewService addReviewService, RemoveReviewService removeReviewService, ConsoleUtils console) {
        this.addReviewService = addReviewService;
        this.removeReviewService = removeReviewService;
        this.console = console;
    }

    public void addReview() {
        try {
            String comment = console.getString("Enter the review comment: ");
            Integer score = console.getInteger("Enter the review score: ");
            String type = console.getString("Enter the type of review (Dish or Restaurant): ");

            Review review;
            switch (type.toLowerCase()) {
                case "dish":
                    Dish dish = (Dish) console.getObject("Enter the name of the dish: ");
                    review = new DishReviewFactory(dish).createReview(comment, score);
                    break;
                case "restaurant":
                    Restaurant restaurant = (Restaurant) console.getObject("Enter the name of the restaurant: ");
                    review = new RestaurantReviewFactory(restaurant).createReview(comment, score);
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type entered");
            }

            addReviewService.setReview(review);
            addReviewService.execute();
            System.out.println("Review added: " + review.getComment());
        } catch (Exception e) {
            System.out.println("Error adding the review: " + e.getMessage());
        }
    }

    public void removeReview() {
        try {
            String comment = console.getString("Enter the comment of the review to remove: ");
            Review review = removeReviewService.findByComment(comment);
            if (review != null) {
                removeReviewService.setReview(review);
                removeReviewService.execute();
                System.out.println("Review removed: " + review.getComment());
            } else {
                System.out.println("Review not found.");
            }
        } catch (Exception e) {
            System.out.println("Error removing the review: " + e.getMessage());
        }
    }
}