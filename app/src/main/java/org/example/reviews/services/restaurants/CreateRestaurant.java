package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class CreateRestaurant implements ICommand<Restaurant> {
    private RestaurantRepository restaurantRepository;
    private ConsoleUtil console;

    public CreateRestaurant(ConsoleUtil console) {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.console = console;
    }

    @Override
    public Restaurant execute() {
        Integer id = console.readInt("Introduzca el id del restaurante: ");
        String name = console.readLine("Introduzca el nombre del restaurante: ");
        String address = console.readLine("Introduzca la direccion del restaurante: ");
        String schedule = console.readLine("Introduzca el horario del restaurante: ");
        Restaurant restaurant = new Restaurant(id, name, address, schedule);
        restaurantRepository.save(restaurant);

        return restaurant;
    }
}
