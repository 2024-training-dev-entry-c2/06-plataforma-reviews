package org.example.repositories;

import org.example.models.Dish;

import java.util.LinkedList;
import java.util.List;

public class DishRepository {
    private static DishRepository instance;
    private final List<Dish> dishes;

    private DishRepository() {
        dishes = new LinkedList<>();
    }

    public static DishRepository getInstance() {
        if (instance == null) {
            instance = new DishRepository();
        }
        return instance;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public Dish findByName(String name) {
        for (Dish dish : dishes) {
            if (dish.getName().equals(name)) {
                return dish;
            }
        }
        return null;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}