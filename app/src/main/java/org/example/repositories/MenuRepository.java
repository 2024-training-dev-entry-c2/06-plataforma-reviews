package org.example.repositories;

import org.example.models.Dish;
import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.observer.Observer;
import org.example.observer.Subject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MenuRepository implements Subject {
    private static MenuRepository instance;
    private final Map<Restaurant, Menu> menus;
    private final List<Observer> observers;

    private MenuRepository() {
        menus = new HashMap<>();
        observers = new ArrayList<>();
    }

    public static MenuRepository getInstance() {
        if (instance == null) {
            instance = new MenuRepository();
        }
        return instance;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public Menu getMenuByRestaurant(Restaurant restaurant) {
        return menus.get(restaurant);
    }

    public void addDishToMenu(Restaurant restaurant, Dish dish) {
        Menu menu = menus.computeIfAbsent(restaurant, r -> new Menu(r, new LinkedList<>()));
        menu.getDishes().add(dish);
        restaurant.setMenu(menu);

        String message = "Se agregó el plato '" + dish.getName() + "' al restaurante '" + restaurant.getName() + "'.";
        notifyObservers(message);
    }

    public void removeDishFromMenu(Restaurant restaurant, Dish dish) {
        Menu menu = menus.get(restaurant);
        if (menu != null) {
            menu.getDishes().remove(dish);
        }
    }

    public void clearMenu(Restaurant restaurant) {
        Menu menu = menus.get(restaurant);
        if (menu != null) {
            menu.getDishes().clear();
        }
    }
}