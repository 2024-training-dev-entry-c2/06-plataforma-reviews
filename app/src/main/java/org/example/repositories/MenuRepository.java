package org.example.repositories;

import org.example.models.DishFood;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.models.ReviewDish;

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


    public void addDishFood(Restaurant restaurant, DishFood newDishFood) {
        restaurant.getMenu().getDishFoodList().stream()
                .filter(dishFood -> dishFood.equals(dishFood))
                .findFirst()
                .ifPresentOrElse(
                        existingDish -> System.out.println("El plato " + newDishFood.getName() + " ya existe en el menú!"),
                        () -> {
                            restaurant.getMenu().getDishFoodList().add(newDishFood);
                            //crear un review basica
                            newDishFood.getReviewList().add(new ReviewDish("No cuenta",2.5F,2.5F));
                            System.out.println("Plato " + newDishFood.getName() + " agregado al menú!");
                        }
                );
    }

    public boolean removeDishFood(Restaurant restaurant, Integer IndexDishFood) {
        DishFood dishFood = restaurant.getMenu().getDishFoodList().get(IndexDishFood);
        return restaurant.getMenu().getDishFoodList().removeIf(dishFoods -> dishFood.equals(dishFood));
    }


    public void showDishes(Menu menu) {
        menu.getDishFoodList().forEach((dishFood -> {
            AtomicInteger counter = new AtomicInteger(1);
            System.out.println(counter.getAndIncrement() + ". " + dishFood.getName());
            System.out.println(dishFood.toString());
            dishFood.getAverageRating();
        }));
    }

}
