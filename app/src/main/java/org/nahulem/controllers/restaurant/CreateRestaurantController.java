package org.nahulem.controllers.restaurant;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.Restaurant;
import org.nahulem.services.restaurant.CreateRestaurantService;
import org.nahulem.utils.Validator;

import java.util.Scanner;

public class CreateRestaurantController implements ICommandController {
    private final CreateRestaurantService createRestaurantService;
    private Validator validator = new Validator(new Scanner(System.in));

    public CreateRestaurantController(CreateRestaurantService createRestaurantService) {
        this.createRestaurantService = createRestaurantService;
    }

    @Override
    public void execute() {
        Restaurant restaurant = createRestaurantService.execute();
        validator.printMessage("Restaurante creado exitosamente: " + restaurant.toString());
    }
}
