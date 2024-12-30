package restaurant.models;

import restaurant.Interface.IReview;
import restaurant.observer.IObserver;

import java.util.LinkedList;
import java.util.List;

public class Restaurant {
    private String name;
    private String address;
    private Menu menu;
    private List<IReview> restaurantReviews = new LinkedList<>();
    private List<IObserver> observers = new LinkedList<>();
    private double averageRating;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.averageRating = 5.0;
    }

    public void addReview(IReview review) {
        if (review instanceof RestaurantReview) {
            restaurantReviews.add(review);
            updateAverageRating();
            notifyObservers("Una nueva reseña ha sido agregada al restaurante: " + name);
        } else {
            throw new IllegalArgumentException("Invalid review type for restaurant");
        }
    }

    private void updateAverageRating() {
        if (restaurantReviews.isEmpty()) {
            averageRating = 5.0;
        } else {
            double total = 0;
            for (IReview review : restaurantReviews) {
                total += review.getQualification();
            }
            averageRating = total / restaurantReviews.size();
        }
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    private void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public double getAverageRating() {
        return averageRating;
    }

    public List<IReview> getRestaurantReviews() {
        return restaurantReviews;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}