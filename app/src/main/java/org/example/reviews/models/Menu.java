package org.example.reviews.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Integer id;
    private String name;
    private Restaurant restaurant;
    private List<Dish> dishes;

    public Menu(Integer id, String name, Restaurant restaurant) {
        this.id = id;
        this.name = name;
        this.restaurant = restaurant;
        this.dishes = new ArrayList<>();
    }

    public Menu() {
    }

    public Integer getId() {
        return id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", restaurant=" + restaurant.getName() +
                ", dishes=" + dishes.toString() +
                '}';
    }
}
