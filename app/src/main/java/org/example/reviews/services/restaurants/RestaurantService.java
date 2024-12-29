package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.services.interfaces.IRestaurant;
import org.example.reviews.utils.ConsoleUtil;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class RestaurantService implements IRestaurant {
    private final CreateRestaurant createRestaurant;

    public RestaurantService(CreateRestaurant createRestaurant){
        this.createRestaurant = createRestaurant;
    }

    @Override
    public Restaurant createRestaurant() {
        return createRestaurant.execute();
    }

    @Override
    public void findAllRestaurants() {

    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

    }

    @Override
    public void removeRestaurant(Integer id) {

    }
}
