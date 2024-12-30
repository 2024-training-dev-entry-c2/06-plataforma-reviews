package org.example.reviews.services.restaurants;

import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

public class RemoveRestaurantImpl implements ICommand<Boolean> {
    private RestaurantRepository restaurantRepository;
    private ConsoleUtil console;

    public RemoveRestaurantImpl(ConsoleUtil console){
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.console = console;
    }
    @Override
    public Boolean execute() {
        int id = console.readInt("Introduzca el id del restaurante: ");
        return restaurantRepository.removeRestaurant(id);
    }
}
