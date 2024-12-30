package restaurant.utils;

import restaurant.models.Dish;
import restaurant.models.Menu;

public class DefaultMenu {
    public static Menu getDefaultMenu() {
        Menu menu = new Menu();
        menu.addDish(new Dish("Plato 1", "Descripción del Plato 1", 10.0));
        menu.addDish(new Dish("Plato 2", "Descripción del Plato 2", 15.0));
        menu.addDish(new Dish("Plato 3", "Descripción del Plato 3", 20.0));
        return menu;
    }
}