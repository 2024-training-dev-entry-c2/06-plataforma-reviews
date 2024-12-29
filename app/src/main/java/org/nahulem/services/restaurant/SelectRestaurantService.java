package org.nahulem.services.restaurant;

import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

import java.util.Map;

public class SelectRestaurantService implements ICommand<Restaurant> {
    private final DataRepository repository;
    private final Validator validator;

    public SelectRestaurantService(DataRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Restaurant execute() {
        Map<Integer, Restaurant> restaurants = repository.getAllRestaurants();

        showRestaurants(restaurants);

        int index = selectRestaurant(restaurants.size());

        return restaurants.get(index - 1);
    }

    private int selectRestaurant(int size) {
        int index = validator.readInt("Ingresa el número del restaurante que deseas seleccionar: ");

        if (index < 1 || index > size) {
            validator.printMessage("El número ingresado no es válido, intenta de nuevo.");
            return selectRestaurant(size);
        }

        return index;
    }

    private void showRestaurants(Map<Integer, Restaurant> restaurants) {
        validator.printMessage("Lista de Restaurantes: ");
        restaurants.forEach((key, value) -> System.out.println((key + 1) + ". " + value.getName()));
    }
}
