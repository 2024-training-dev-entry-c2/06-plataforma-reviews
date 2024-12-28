package org.example.repositories;

import org.example.models.DishFood;
import org.example.models.Menu;
import org.example.models.Restaurant;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MenuRepository {
    private static MenuRepository instance;
    private final List<Menu> menus = new LinkedList<>();

    public MenuRepository() {

    }

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    public void addMenu(Restaurant restaurant, Menu menu) {
        menus.stream()
                .filter(existing -> existing.getName().equals(restaurant.getName()))
                .findFirst()
                .ifPresentOrElse(
                        existing -> System.out.println("Restaurante: " + restaurant.getName() + " ya existe!"),
                        () -> {
                            restaurant.setMenu(menu);
                            menus.add(menu);
                        }
                );
    }

    public void addDishFood(String menuCreated, DishFood dishFood) {
        menus.stream()
                .filter(menu -> menu.getName().equals(menuCreated))
                .findFirst()
                .ifPresentOrElse(
                        menu -> menu.addDishfood(dishFood),
                        () -> System.out.println("Menú no encontrado: " + menuCreated)
                );
    }

    public boolean removeDishFood(Menu menu, DishFood dishFood) {
        return menu.removeDishFood(dishFood);
    }

    public void updateDishFood(Menu menuUpdated, DishFood newDishFood) {
        menus.stream()
                .filter(menu -> menu.equals(menuUpdated))
                .findFirst().flatMap(menu -> menu.getDishFoodList().stream()
                        .filter(dish -> dish.getName().equals(newDishFood.getName()))
                        .findFirst()).ifPresent(dish -> {
                    dish.setName(newDishFood.getName());
                    dish.setPrice(newDishFood.getPrice());
                });

    }

    public void showDishes(Menu menu) {
        menu.getDishFoodList().forEach((dishFood -> {
            AtomicInteger counter = new AtomicInteger(1);
            System.out.println(counter.getAndIncrement() + ". " + dishFood.getName());
        }));
    }

}
