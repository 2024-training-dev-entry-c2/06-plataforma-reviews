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
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            return null;
        }

        Restaurant existingRestaurant = repository.getRestaurant(restaurant.getRestaurantId());

        if (existingRestaurant == null) {
            validator.printMessage("El restaurante no existe.");
            return null;
        }

        updateRestaurant(existingRestaurant);

        notifyObservers(existingRestaurant);

        return repository.updateRestaurant(existingRestaurant);
    }

    private void updateRestaurant(Restaurant existingRestaurant) {
        validator.printMessage("Actualización de Restaurante (deje vacío para no modificar): ");
        String name = validator.readString("Ingresa el nuevo nombre del restaurante: ");
        existingRestaurant.setName(validateOrKeep(name, existingRestaurant.getName()));

        String description = validator.readString("Ingresa la nueva descripción del restaurante: ");
        existingRestaurant.setDescription(validateOrKeep(description, existingRestaurant.getDescription()));

        String location = validator.readString("Ingresa la nueva ubicación del restaurante: ");
        existingRestaurant.setLocation(validateOrKeep(location, existingRestaurant.getLocation()));
    }

    private void notifyObservers(Restaurant restaurant) {
        String message = "El restaurante " + restaurant.getName() + " ha sido actualizado.";
        restaurant.notifyObservers(message);
        validator.printMessage("Notificación enviada a los observadores del restaurante.");
    }

    private String validateOrKeep(String newValue, String existingValue) {
        return newValue.isEmpty() ? existingValue : newValue;
    }
}
