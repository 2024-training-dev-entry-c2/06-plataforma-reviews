package org.example.reviews.repositories;

import org.example.reviews.models.Dish;

import java.util.LinkedList;
import java.util.List;

public class DishRepository {
    private static DishRepository INSTANCE;
    private List<Dish> dishes;

    private DishRepository(){
        this.dishes = new LinkedList<>();
    }

    public static synchronized DishRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DishRepository();
        }
        return INSTANCE;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void addDish(Dish dish){
        dishes.add(dish);
    }
}