package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

public class UpdateRestaurantImpl implements ICommand<Restaurant> {
    private RestaurantRepository restaurantRepository;
    private ConsoleUtil console;

    public UpdateRestaurantImpl(ConsoleUtil console) {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.console = console;
    }
    @Override
    public Restaurant execute() {
        Integer restaurantId = console.readInt("Introduzca el id del restaurante: ");
        Restaurant restaurantToUpdate = restaurantRepository.findRestaurantById(restaurantId);
        String name = console.readLine("Introduzca el nuevo nombre del restaurante: ");
        String address = console.readLine("Introduzca la nueva direccion del restaurante: ");
        String schedule = console.readLine("Introduzca el nuevo horario del restaurante: ");
        restaurantToUpdate.setName(name);
        restaurantToUpdate.setAddress(address);
        restaurantToUpdate.setSchedule(schedule);

        restaurantRepository.save(restaurantToUpdate);

        return restaurantToUpdate;
    }
}
