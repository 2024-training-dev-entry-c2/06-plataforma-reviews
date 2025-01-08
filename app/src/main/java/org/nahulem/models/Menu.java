package org.nahulem.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private static Integer idCounter = 0;
    private Integer menuId;
    private String name;
    private List<Dish> dishes;

    public Menu(String name, List<Dish> dishes) {
        this.menuId = generateId();
        this.name = name;
        this.dishes = dishes != null ? dishes : new ArrayList<>();
    }

    private Integer generateId() {
        return ++idCounter;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(name).append("\n");
        result.append("Platos:\n");
        dishes.forEach(dish -> result.append("  - ").append(dish.toString()).append("\n"));
        return result.toString();
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public String getName() {
        return name;
    }
}
