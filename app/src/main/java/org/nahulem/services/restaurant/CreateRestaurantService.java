package org.nahulem.services.restaurant;

import org.nahulem.models.Menu;
import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.menu.AddDishService;
import org.nahulem.utils.Validator;

public class CreateRestaurantService implements ICommand<Restaurant> {
    private DataRepository repository;
    private Validator validator;
    private AddDishService addDishService;

    public CreateRestaurantService(AddDishService addDishService, DataRepository repository, Validator validator) {
        this.addDishService = addDishService;
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

        Menu menu = addDishService.execute();
        restaurant.setMenu(menu);

        System.out.println("Menú creado exitosamente para el restaurante: " + restaurant.getName() + "\n");

        return restaurant;
    }
}
