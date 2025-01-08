package org.nahulem.services.restaurant;


import org.nahulem.models.Restaurant;
import org.nahulem.repositories.RestaurantRepository;
import org.nahulem.services.interfaces.ICommand;

public class DeleteRestaurantService implements ICommand<Boolean> {
    private final RestaurantRepository repository;
    private final SelectRestaurantService selectRestaurantService;

    public DeleteRestaurantService(RestaurantRepository repository, SelectRestaurantService selectRestaurantService) {
        this.repository = repository;
        this.selectRestaurantService = selectRestaurantService;
    }

    @Override
    public Boolean execute() {
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            return false;
        }

        return repository.deleteRestaurant(restaurant.getRestaurantId());
    }
}
