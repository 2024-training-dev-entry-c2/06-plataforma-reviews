package org.nahulem.services.menu;

import org.nahulem.models.Dish;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.utils.Validator;

public class UpdateDishService implements ICommand<Boolean> {
    private final Validator validator;
    private final DataRepository repository;
    private final SelectDishService selectDishService;

    public UpdateDishService(DataRepository repository, SelectDishService selectDishService, Validator validator) {
        this.repository = repository;
        this.selectDishService = selectDishService;
        this.validator = validator;
    }

    @Override
    public Boolean execute() {
        Dish selectedDish = selectDishService.execute();

        if (selectedDish == null) {
            return null;
        }

        updateDish(selectedDish);

        if (selectedDish == null) {
            validator.printMessage("El restaurante no existe.");
            return null;
        }

        updateDish(selectedDish);

        return true;
    }

    private void updateDish(Dish existingDish) {
        validator.printMessage("Actualización de Plato (deje vacío para no modificar): ");
        String name = validator.readString("Ingresa el nuevo nombre del plato: ");
        existingDish.setName(validateOrKeep(name, existingDish.getName()));

        String description = validator.readString("Ingresa la nueva descripción del plato: ");
        existingDish.setDescription(validateOrKeep(description, existingDish.getDescription()));

        String price = validator.readString("Ingresa el nuevo precio del plato: ");
        if (!price.isEmpty()) {
            existingDish.setPrice(Float.parseFloat(price));
        }
    }


    private String validateOrKeep(String newValue, String existingValue) {
        return newValue.isEmpty() ? existingValue : newValue;
    }
}
