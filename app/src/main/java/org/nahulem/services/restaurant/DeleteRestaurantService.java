package org.nahulem.services.restaurant;


import org.nahulem.models.Restaurant;
import org.nahulem.repositories.DataRepository;
import org.nahulem.services.interfaces.ICommand;

public class DeleteRestaurantService implements ICommand<Boolean> {
    private final DataRepository repository;
    private final SelectRestaurantService selectRestaurantService;

    public DeleteRestaurantService(DataRepository repository, SelectRestaurantService selectRestaurantService) {
        this.repository = repository;
        this.selectRestaurantService = selectRestaurantService;
    }

    @Override
    public Boolean execute() {
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            System.out.println("No se ha seleccionado un restaurante.");
            return false;
        }

        return repository.deleteRestaurant(restaurant.getRestaurantId());
    }
}
