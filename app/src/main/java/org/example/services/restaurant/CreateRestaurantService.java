package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.DataRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.menu.CreateMenuService;
import org.example.utils.Validator;

public class CreateRestaurantService implements ICommand<Restaurant> {
    private DataRepository repository;
    private Validator validator;
    private CreateMenuService createMenuService;

    public CreateRestaurantService(CreateMenuService createMenuService, DataRepository repository, Validator validator) {
        this.createMenuService = createMenuService;
        this.repository = repository;
        this.validator = validator;
    }

    @Override
    public Restaurant execute() {
        String name = validator.readString("Ingresa el nombre del restaurante: ");
        String location = validator.readString("Ingresa la ubicacion del restaurante: ");
        String description = validator.readString("Ingresa la descripcion del restaurante: ");

        Restaurant restaurant = new Restaurant(name, location, description, null);
        repository.addRestaurant(restaurant);

        System.out.println(
                "Restaurante creado exitosamente: " + restaurant.getName() + "\n" +
                "\nCreando menú para el restaurante..."
        );

        Menu menu = createMenuService.execute();
        restaurant.setMenu(menu);

        System.out.println("Menú creado exitosamente para el restaurante: " + restaurant.getName() + "\n");

        return restaurant;
    }
}
