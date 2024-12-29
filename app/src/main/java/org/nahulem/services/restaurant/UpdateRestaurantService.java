package org.nahulem.services.restaurant;

import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

public class UpdateRestaurantService implements ICommand<Boolean> {
    private final SelectRestaurantService selectRestaurantService;
    private final DataRepository repository;
    private final Validator validator;

    public UpdateRestaurantService(DataRepository repository, SelectRestaurantService selectRestaurantService, Validator validator) {
        this.repository = repository;
        this.selectRestaurantService = selectRestaurantService;
        this.validator = validator;
    }

    @Override
    public Boolean execute() {
        Restaurant existingRestaurant = validateExistingRestaurant();
        if (existingRestaurant == null) return false;

        updateRestaurant(existingRestaurant);
        repository.updateRestaurant(existingRestaurant);

        return true;
    }

    private Restaurant validateExistingRestaurant() {
        Restaurant restaurant = selectRestaurantService.execute();
        Restaurant existingRestaurant = repository.getRestaurant(restaurant.getRestaurantId());

        if (existingRestaurant == null) {
            validator.printMessage("El restaurante no existe.");
            return null;
        }
        return existingRestaurant;
    }

    private void updateRestaurant(Restaurant existingRestaurant) {
        validator.printMessage("Actualización de Restaurante (deje vacío para no modificar): ");
        String name = validator.readString("Ingresa el nuevo nombre del restaurante: ");
        isEmptyValidator(name, existingRestaurant.getName());
        String description = validator.readString("Ingresa la nueva descripción del restaurante: ");
        isEmptyValidator(description, existingRestaurant.getDescription());
        String location = validator.readString("Ingresa la nueva ubicación del restaurante: ");
        isEmptyValidator(location, existingRestaurant.getLocation());
    }

    private void isEmptyValidator(String value, String existingValue) {
        if (value.isEmpty()) {
            value = existingValue;
        }
    }
}
