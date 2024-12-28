package org.example.services.restaurant;

import org.example.models.Menu;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.utils.IValidatorScanner;

import java.util.Collections;
import java.util.List;

public class AddMenuRestaurant implements ICommand {
    private final RestaurantRepository repository = RestaurantRepository.getInstance();
    private final MenuRepository menuRepository = MenuRepository.getInstance();
    private final IValidatorScanner validatorScanner;

    public AddMenuRestaurant(IValidatorScanner validatorScanner) {
        this.validatorScanner = validatorScanner;
    }

    private void addMenu(Restaurant restaurant, String menuName){
        Menu menu = new Menu(menuName, Collections.emptyList());
        menuRepository.addMenu(restaurant,menu);
    }

    @Override
    public void execute() {
//        repository.displayRestaurants();
//        int optionRestaurant = validatorScanner.integerScanner("Selecciona el restaurante para reseñar :");
//        Restaurant restaurant = repository.getRestaurant(optionRestaurant);
//        String menu = validatorScanner.stringScanner("Escribe el menu que quieres en el restaurante");
//        addMenu(restaurant,menu);
//        return null;
    }
}
