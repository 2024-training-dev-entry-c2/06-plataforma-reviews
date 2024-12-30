package org.example.reviews.models;

import org.example.reviews.models.interfaces.IRating;
import org.example.reviews.observer.Observer;
import org.example.reviews.observer.Subject;
import org.example.reviews.repositories.RestaurantReviewRepository;

import java.util.ArrayList;
import java.util.List;



/**
 * @author Manuel Aguilera / @aguileradev
 */
public class Restaurant implements Subject, IRating {
    private Integer id;
    private String name;
    private String address;
    private String schedule;
    private List<Menu> menus;
    private List<RestaurantReview> reviews;
    private List<Observer> observers = new ArrayList<>();

    public Restaurant(Integer id, String name, String address, String schedule) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.schedule = schedule;
        this.menus = new ArrayList<>();
        this.reviews = new ArrayList<>();
    }

    public Restaurant() {}

    public void addMenu(Menu menu) {
        this.menus.add(menu);
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
        observers.forEach(observer -> observer.update(message));
    }

    public void addReview(RestaurantReview review) {
        reviews.add(review);
        notifyObservers("Nuevo comentario agregada al restaurante " + name);
    }

    @Override
    public Float calculateAverageRating() {
        List<RestaurantReview> reviews = getReviewsByRestaurant();
        float average = 0.0f;
        if (!reviews.isEmpty()) {
            average = (float) reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
        }

        return average;
    }

    private List<RestaurantReview> getReviewsByRestaurant() {
        List<RestaurantReview> reviews = RestaurantReviewRepository.getINSTANCE()
                .getReviews()
                .stream()
                .filter(review -> review.getRestaurantId().equals(this.id))
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }

    public List<RestaurantReview> getReviews() {
        return reviews;
    }

    public void setReviews(List<RestaurantReview> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", schedule='" + schedule + '\'' +
                ", menus=" + menus.size() +
                ", reviews=" + reviews +
                '}';
    }


}
