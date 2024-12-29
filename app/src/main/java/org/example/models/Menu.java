package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private List<DishFood> dishFoodList;

    public Menu(String name) {
        this.name = name;
        this.dishFoodList = new ArrayList<>();
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
