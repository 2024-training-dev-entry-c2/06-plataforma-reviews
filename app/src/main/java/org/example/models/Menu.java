package org.example.models;

import java.util.LinkedList;

public class Menu {
    private Restaurant restaurant;
    private LinkedList<Dish> dishes;

    public Menu() {
        this.dishes = new LinkedList<>();
    }

    public Menu(Restaurant restaurant, LinkedList<Dish> dishes) {
        this.restaurant = restaurant;
        this.dishes = dishes;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "restaurant=" + restaurant +
                ", dishes=" + dishes +
                '}';
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LinkedList<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(LinkedList<Dish> dishes) {
        this.dishes = dishes;
    }
}