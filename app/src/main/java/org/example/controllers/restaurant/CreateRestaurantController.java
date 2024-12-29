package org.example.controllers.restaurant;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.Restaurant;
import org.example.services.restaurant.CreateRestaurantService;

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
