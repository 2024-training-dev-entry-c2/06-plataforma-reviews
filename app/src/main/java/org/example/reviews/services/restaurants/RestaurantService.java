package org.example.reviews.services.restaurants;

import org.example.reviews.models.Restaurant;
import org.example.reviews.services.interfaces.IRestaurant;

import java.util.Map;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public class RestaurantService implements IRestaurant {
    private final CreateRestaurantImpl createRestaurantImpl;
    private final FindRestaurantsImpl findRestaurantsImpl;
    private final UpdateRestaurantImpl updateRestaurantImpl;
    private final RemoveRestaurantImpl removeRestaurantImpl;

    public RestaurantService(CreateRestaurantImpl createRestaurantImpl,
                             FindRestaurantsImpl findRestaurantsImpl,
                             UpdateRestaurantImpl updateRestaurantImpl,
                             RemoveRestaurantImpl removeRestaurantImpl) {
        this.createRestaurantImpl = createRestaurantImpl;
        this.findRestaurantsImpl = findRestaurantsImpl;
        this.updateRestaurantImpl = updateRestaurantImpl;
        this.removeRestaurantImpl = removeRestaurantImpl;
    }

    @Override
    public Restaurant createRestaurant() {
        return createRestaurantImpl.execute();
    }

    @Override
    public Map<Integer, Restaurant> findAllRestaurants() {
        return findRestaurantsImpl.execute();
    }

    @Override
    public Restaurant updateRestaurant() {
        return updateRestaurantImpl.execute();

    }

    @Override
    public Boolean removeRestaurant() {
        return removeRestaurantImpl.execute() ;
    }
}
