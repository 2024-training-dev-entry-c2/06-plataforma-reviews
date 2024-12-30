package org.nahulem.services.restaurant;

import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

import java.util.Map;

public class SelectRestaurantService implements ICommand<Restaurant> {
    private final RestaurantRepository repository;
    private final Validator validator;

    public SelectRestaurantService(RestaurantRepository repository, Validator validator) {
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Restaurant execute() {
        Map<Integer, Restaurant> restaurants = repository.getAllRestaurants();

        if (restaurants.isEmpty()) {
            return null;
        }

        showRestaurants(restaurants);

        int index = selectRestaurant(restaurants.size());

        return restaurants.get(index);
    }

    private int selectRestaurant(int size) {
        while (true) {
            int index = validator.readInt("Ingresa el número del restaurante que deseas seleccionar: \n");
            if (index >= 1 && index <= size) {
                return index;
            }
            validator.printMessage("El número ingresado no es válido, intenta de nuevo.");
        }
    }



    private void showRestaurants(Map<Integer, Restaurant> restaurants) {
        validator.printMessage("Lista de Restaurantes: \n-------------------");
        restaurants.forEach((key, value) ->
                validator.printMessage(key + ". " +
                        value.getName() + "\n" +
                        value.getLocation() + "\n" +
                        value.getDescription() + "\n" +
                        "-------------------")
        );
    }
}
