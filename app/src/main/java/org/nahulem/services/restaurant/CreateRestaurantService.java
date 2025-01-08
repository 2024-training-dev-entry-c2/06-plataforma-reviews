package org.nahulem.services.restaurant;

import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.models.UserObserver;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.utils.Validator;

public class CreateRestaurantService implements ICommand<Restaurant> {
    private RestaurantRepository repository;
    private Validator validator;
    private AddDishService addDishService;

    public CreateRestaurantService(AddDishService addDishService, RestaurantRepository repository, Validator validator) {
        this.addDishService = addDishService;
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Restaurant execute() {
        validator.printMessage("\n---Creando restaurante---\n");
        String name = validator.readString("Ingresa el nombre del restaurante: \n");
        String location = validator.readString("Ingresa la ubicacion del restaurante: \n");
        String description = validator.readString("Ingresa la descripcion del restaurante: \n");

        Restaurant restaurant = new Restaurant(name, location, description, null);
        repository.addRestaurant(restaurant);

        validator.printMessage(
                "Restaurante creado exitosamente: " + restaurant.getName() + "\n");

        Menu menu = addDishService.execute();
        restaurant.setMenu(menu);

        validator.printMessage("Menú creado exitosamente para el restaurante: " + restaurant.getName() + "\n");

        registerObserver(restaurant);

        return restaurant;
    }

    private void registerObserver(Restaurant restaurant) {
        UserObserver adminObserver = new UserObserver("Admin");
        restaurant.addObserver(adminObserver);
        validator.printMessage("Administrador registrado como observador para el restaurante: " + restaurant.getName());
    }
}
