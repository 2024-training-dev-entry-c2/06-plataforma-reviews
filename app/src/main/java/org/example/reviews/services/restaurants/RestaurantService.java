package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.services.interfaces.IRestaurant;
import org.example.reviews.utils.ConsoleUtil;

import java.util.Map;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class RestaurantService implements IRestaurant {
    private final CreateRestaurant createRestaurant;
    private final FindRestaurants findRestaurants;

    public RestaurantService(CreateRestaurant createRestaurant, FindRestaurants findRestaurants) {
        this.createRestaurant = createRestaurant;
        this.findRestaurants = findRestaurants;
    }

    @Override
    public Restaurant createRestaurant() {
        return createRestaurant.execute();
    }

    @Override
    public Map<Integer, Restaurant> findAllRestaurants() {
        return findRestaurants.execute();
    }

    @Override
    public void updateRestaurant(Restaurant restaurant) {

    }

    @Override
    public void removeRestaurant(Integer id) {

    }
}
