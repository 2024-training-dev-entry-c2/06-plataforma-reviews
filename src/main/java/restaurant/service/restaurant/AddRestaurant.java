package restaurant.service.restaurant;

import restaurant.models.Restaurant;
import restaurant.repository.RestaurantRepository;
import restaurant.service.Interfaces.ICommand;
import restaurant.utils.ConsoleUtils;
import restaurant.utils.DefaultMenu;


public class AddRestaurant implements ICommand<Restaurant> {
    private final RestaurantRepository repository;
    private final ConsoleUtils console;

    public AddRestaurant(RestaurantRepository repository, ConsoleUtils console) {
        this.repository = repository;
        this.console = console;
    }

    @Override
    public Restaurant execute() {
        String name = console.getString("Ingrese el nombre del restaurante: ");
        String address = console.getString("Ingrese la dirección del restaurante: ");
        Restaurant restaurant = new Restaurant(name, address);
        restaurant.setMenu(DefaultMenu.getDefaultMenu());
        repository.addRestaurant(restaurant);
        return restaurant;
    }
}