package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;

import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

public class AddRestaurant implements ICommand {
    private final RestaurantRepository repository;
    private final IValidatorScanner validatorScanner;

    public AddRestaurant(RestaurantRepository repository, IValidatorScanner validatorScanner) {
        this.repository = repository;
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String name = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        String address = validatorScanner.stringScanner("Escribe el direccion del Restaurante");
        Restaurant restaurant = new Restaurant(name, address);
        Menu menu = new Menu((name + " Menu"));
        restaurant.setMenu(menu);
        repository.addRestaurant(restaurant); // Uso directo del repositorio inyectado
        System.out.println("Restaurant added: " + name);
    }
}
