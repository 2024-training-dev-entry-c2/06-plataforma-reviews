package restaurant.models;

import restaurant.observer.IObserver;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Menu {
    private Restaurant restaurant;
    private List<Dish> dishes;
    private List<IObserver> observers = new LinkedList<>();

    public Menu(Restaurant restaurant) {
        this.restaurant = restaurant;
        this.dishes = new ArrayList<>();
    }

    public Menu() {
        this.dishes = new ArrayList<>();
    }

    public void addReview(DishReview review) {
        review.getDish().getReviews().add(review);
        notifyObservers("New review added to dish: " + review.getDish().getName());
    }

    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    public List<IObserver> getObservers() {
        return observers;
    }

    public void notifyObservers(String message) {
        for (IObserver observer : observers) {
            observer.update(message);
        }
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void removeDish(Dish dish) {
        dishes.remove(dish);
    }
}