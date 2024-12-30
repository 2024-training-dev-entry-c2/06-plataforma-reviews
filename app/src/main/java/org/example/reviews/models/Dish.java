package org.example.reviews.models;

import org.example.reviews.models.interfaces.IRating;
import org.example.reviews.observer.Observer;
import org.example.reviews.observer.Subject;
import org.example.reviews.repositories.DishReviewRepository;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Manuel Aguilera / @aguileradev
 */
public class Dish implements Subject, IRating {
    private Integer id;
    private String name;
    private Double price;
    private List<DishReview> reviews;
    private List<Observer> observers = new ArrayList<>();

    public Dish(Integer id, String name, Double price) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.reviews = new ArrayList<>();
    }

    public Dish() {
    }
    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        observers.forEach((observer) -> observer.update(message));

    }

    public void addReview(DishReview review) {
        reviews.add(review);
        notifyObservers("Nuevo comentario agregado al plato " + name);
    }

    @Override
    public Float calculateAverageRating() {
        List<DishReview> reviews = getReviewsByDish();
        float average = 0.0f;
        if (!reviews.isEmpty()) {
            average = (float) reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
        }

        return average;
    }

    private List<DishReview> getReviewsByDish() {
        List<DishReview> reviews = DishReviewRepository.getINSTANCE()
                .getReviews()
                .stream()
                .filter(review -> review.getDishId().equals(this.id))
                .toList();
        return reviews;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<DishReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<DishReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", reviews=" + reviews +
                '}';
    }
}
