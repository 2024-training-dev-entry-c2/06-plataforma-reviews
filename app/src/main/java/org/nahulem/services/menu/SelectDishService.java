package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.models.Restaurant;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.restaurant.SelectRestaurantService;
import org.nahulem.utils.Validator;

import java.util.List;

public class SelectDishService implements ICommand<Dish> {
    private final Validator validator;
    private final SelectRestaurantService selectRestaurantService;

    public SelectDishService(Validator validator, SelectRestaurantService selectRestaurantService) {
        this.validator = validator;
        this.selectRestaurantService = selectRestaurantService;
    }

    @Override
    public Dish execute() {
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            return null;
        }

        List<Dish> dishes = restaurant.getMenu().getDishes();

        if (dishes.isEmpty()) {
            System.out.println("No hay platos en el menú.");
            return null;
        }

        showDishes(dishes);
        Integer index = getSelectedDish(dishes.size());

        return dishes.get(index - 1);
    }

    private void showDishes(List<Dish> dishes) {
        validator.printMessage("Lista de Platos: \n-------------------");
        dishes.forEach(dish -> validator.printMessage((dishes.indexOf(dish)) + ". " + dish.getName() + "\n" + "-------------------")
        );
    }

    private int getSelectedDish(int size) {
        int index = validator.readInt("Ingresa el número del plato que deseas seleccionar: ");

        if (index < 1 || index > size) {
            validator.printMessage("El número ingresado no es válido, intenta de nuevo.");
            return getSelectedDish(size);
        }

        return index;
    }
}
