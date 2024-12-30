package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

import java.util.Optional;

public class UpdateDishService implements ICommand<Restaurant> {
    private final Validator validator;
    private final RestaurantRepository repository;
    private final SelectDishService selectDishService;

    public UpdateDishService(RestaurantRepository repository, SelectDishService selectDishService, Validator validator) {
        this.repository = repository;
        this.selectDishService = selectDishService;
        this.validator = validator;
    }

    @Override
    public Restaurant execute() {
        Dish selectedDish = selectDishService.execute();

        if (selectedDish == null) {
            return null;
        }

        updateDish(selectedDish);

        Optional<Restaurant> restaurantOptional = repository.getAllRestaurants().values().stream()
                .filter(restaurant -> restaurant.getMenu().getDishes().contains(selectedDish))
                .findFirst();

        return restaurantOptional.orElse(null);
    }

    private void updateDish(Dish existingDish) {
        validator.printMessage("Actualización de Plato (deje vacío para no modificar): ");
        String name = validator.readString("Ingresa el nuevo nombre del plato: ");
        existingDish.setName(validateOrKeep(name, existingDish.getName()));

        String description = validator.readString("Ingresa la nueva descripción del plato: ");
        existingDish.setDescription(validateOrKeep(description, existingDish.getDescription()));

        Float price = validator.readFloat("Ingresa el nuevo precio del plato: ");
        if (price != 0f) {
            existingDish.setPrice(price);
        }
    }

    private String validateOrKeep(String newValue, String existingValue) {
        return newValue.isEmpty() ? existingValue : newValue;
    }
}
