package restaurant.models;

import restaurant.Interface.IReview;

import java.util.ArrayList;
import java.util.List;

public class Dish {
    private String name;
    private String description;
    private double price;
    private List<IReview> reviews = new ArrayList<>();
    private double averageRating;

    public Dish(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.averageRating = 0.0;
    }

    public void addReview(IReview review) {
        if (review instanceof DishReview) {
            if (review.getQualification() < 0 || review.getQualification() > 10) {
                throw new IllegalArgumentException("Qualification must be between 0 and 10");
            }
            reviews.add(review);
            updateAverageRating();
        } else {
            throw new IllegalArgumentException("Invalid review type for dish");
        }
    }

    private void updateAverageRating() {
        if (reviews.isEmpty()) {
            averageRating = 0.0;
        } else {
            double total = 0;
            for (IReview review : reviews) {
                total += review.getQualification();
            }
            averageRating = total / reviews.size();
        }
    }

    public double getAverageRating() {
        return averageRating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setReviews(List<IReview> reviews) {
        this.reviews = reviews;
        updateAverageRating();
    }

    public List<IReview> getDishReviews() { return reviews; }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public List<IReview> getReviews() {
        return reviews;
    }
}