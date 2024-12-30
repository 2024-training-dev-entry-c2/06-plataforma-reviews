package org.example.reviews.models;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class Restaurant {
    private Integer id;
    private String name;
    private String address;
    private String schedule;
    private List<Menu> menus;
    private List<RestaurantReview> reviews;

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
