package org.example.controllers;

import org.example.models.Dish;
import org.example.services.dish.AddDishService;
import org.example.utils.consoleUtils.ConsoleUtils;

public class DishController {
    private final AddDishService addDishService;
    private final ConsoleUtils console;
    public DishController(AddDishService addDishService, ConsoleUtils console) {
        this.addDishService = addDishService;
        this.console = console;
    }

    public void addDish() {
        try {
            String name = console.getString("Enter the name of the dish: ");
            String description = console.getString("Enter the description of the dish: ");
            Double price = console.getDouble("Enter the price of the dish: ");

            Dish dish = new Dish(name, description, price, null);
            addDishService.setDish(dish);
            addDishService.execute();
            System.out.println("Dish added: " + dish.getName());
        } catch (Exception e) {
            System.out.println("Error adding the dish: " + e.getMessage());
        }
    }
}