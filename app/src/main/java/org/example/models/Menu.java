package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private Integer idCounter = 0;
    private Integer menuId;
    private String name;
    private List<Dish> dishes;

    public Menu(String name, List<Dish> dishes) {
        this.menuId = generateId();
        this.name = name;
        this.dishes = new ArrayList<>();
    }

    public Menu() {
    }

    private Integer generateId() {
        return ++idCounter;
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void updateDish(Dish updatedDish) {
        dishes.stream()
                .filter(dish -> dish.getDishId().equals(updatedDish.getDishId()))
                .forEach(dish -> {
                    dish.setName(updatedDish.getName());
                    dish.setDescription(updatedDish.getDescription());
                    dish.setPrice(updatedDish.getPrice());
                });
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

    public Integer getIdCounter() {
        return idCounter;
    }

    public void setIdCounter(Integer idCounter) {
        this.idCounter = idCounter;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}