package org.example.reviews.repositories;

import org.example.reviews.models.Dish;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DishRepository {
    private static DishRepository INSTANCE;
    private Map<Integer, Dish> dishes;

    private DishRepository(){
        this.dishes = new HashMap<>();
    }

    public static synchronized DishRepository getInstance(){
        if(INSTANCE == null){
            INSTANCE = new DishRepository();
        }
        return INSTANCE;
    }
    
    public void addDish(Dish dish){
        dishes.put(dish.getId(), dish);
    }
    
    public Dish getDishById(Integer id){
        return dishes.get(id);
    }

    public Map<Integer, Dish> getDishes() {
        return dishes;
    }

}