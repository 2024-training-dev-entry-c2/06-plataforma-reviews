package org.nahulem.controllers.restaurant;

import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.models.Restaurant;
import org.nahulem.services.restaurant.CreateRestaurantService;

public class CreateRestaurantController implements ICommandController {
    private final CreateRestaurantService createRestaurantService;

    public CreateRestaurantController(CreateRestaurantService createRestaurantService) {
        this.createRestaurantService = createRestaurantService;
    }


    @Override
    public void execute() {
        Restaurant restaurant = createRestaurantService.execute();
        System.out.println("Restaurante creado exitosamente: " + restaurant.toString());
    }
}
