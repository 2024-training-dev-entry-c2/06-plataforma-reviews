package restaurant.utils;

import restaurant.models.Dish;

import java.util.List;

public class DishUtils {

    public void showDishes(List<Dish> dishes) {
        System.out.println("Lista de platos:");
        for (int i = 0; i < dishes.size(); i++) {
            System.out.println((i + 1) + ". " + dishes.get(i).getName());
        }
    }

    public int getDishIndex(ConsoleUtils console, List<Dish> dishes, String message) {
        int index = console.getInteger(message) - 1;
        if (index >= 0 && index < dishes.size()) {
            return index;
        } else {
            System.out.println("Índice no válido.");
            return -1;
        }
    }
}
