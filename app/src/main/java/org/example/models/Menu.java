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



    public List<DishFood> getDishFoodList() {
        return dishFoodList;
    }



}
