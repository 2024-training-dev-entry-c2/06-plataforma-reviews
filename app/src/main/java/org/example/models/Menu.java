package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String id;
    private List<Dish> dishes;

    public Menu(String id) {
        this.id = id;
        this.dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void updateDish(Dish dish) {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getId().equals(dish.getId())) {
                dishes.set(i, dish);
                break;
            }
        }
    }

    public void deleteDish(String dishId) {
        dishes.removeIf(dish -> dish.getId().equals(dishId));
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}