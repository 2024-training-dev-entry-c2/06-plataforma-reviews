package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String menuId;
    private List<Dish> dishes;

    public Menu(String id, List<Dish> dishes) {
        this.menuId = id;
        this.dishes = new ArrayList<>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void updateDish(Dish dish) {
        for (int i = 0; i < dishes.size(); i++) {
            if (dishes.get(i).getDishId().equals(dish.getDishId())) {
                dishes.set(i, dish);
                break;
            }
        }
    }

    public void deleteDish(String dishId) {
        dishes.removeIf(dish -> dish.getDishId().equals(dishId));
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }
}