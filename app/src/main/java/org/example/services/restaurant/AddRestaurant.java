package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

import java.util.Collections;
import java.util.List;

public class AddRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    @Override
    public void execute() {
        String name = validatorScanner.stringScanner("Escribe el nombre del Restaurante");
        String address = validatorScanner.stringScanner("Escribe el direccion del Restaurante");
        Restaurant restaurant = new Restaurant(name, address);
        Menu menu = new Menu((name+" Menu"),Collections.emptyList());
        restaurant.setMenu(menu);
        repository.getInstance().addRestaurant(restaurant);
        System.out.println("Restaurant added: " + name);
    }
}
