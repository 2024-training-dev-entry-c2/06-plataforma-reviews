package org.example.reviews.models;


import java.time.LocalDate;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class DishReview extends Review {
    private Integer dishId;

    public DishReview(Integer dishId, Integer id, String author, String comment, Float rating, LocalDate date) {
        super(id, author, comment, rating, date);
        this.dishId = dishId;
    }

    @Override
    public void reviewType() {
        System.out.println("---Reseña de plato---");
    }
    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
    }

    @Override
    public String toString() {
        return "DishReview: " + super.toString();
    }
}
