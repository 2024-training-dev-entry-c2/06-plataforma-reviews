package org.example.models;

import java.util.List;

public class Menu {
    private String name;
    private List<DishFood> dishFoodList;

    public Menu(String name, List<DishFood> dishFoodList) {
        this.name = name;
        this.dishFoodList = dishFoodList;
    }

    public Menu() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishFood> getDishFoodList() {
        return dishFoodList;
    }

    public void setDishFoodList(List<DishFood> dishFoodList) {
        this.dishFoodList = dishFoodList;
    }
}
