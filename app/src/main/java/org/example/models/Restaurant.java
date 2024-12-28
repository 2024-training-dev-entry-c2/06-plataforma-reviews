package org.example.models;

import org.example.models.interfaces.Observable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Restaurant extends Observable {

    private String name;
    private String address;
    private Menu menu;
    private Map<User,Review> reviewMap;

    public Restaurant(String name, String address) {
        this.name = name;
        this.address = address;
        this.reviewMap = new HashMap<>();
    }
    public void addReview(User user ,Review review) {

        reviewMap.put(user,review);
        notifyObservers("mensaje pronto");//mensaje del review
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

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public Map<User, Review> getReviewMap() {
        return reviewMap;
    }

    public void setReviewMap(Map<User, Review> reviewMap) {
        this.reviewMap = reviewMap;
    }
}
