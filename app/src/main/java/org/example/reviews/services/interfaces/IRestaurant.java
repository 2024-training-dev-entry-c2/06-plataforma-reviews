package org.example.reviews.services.interfaces;

import org.example.reviews.models.Restaurant;

import java.util.Map;

/**
 * @author Manuel Aguilera / @aguileradev
 */
public interface IRestaurant {
    Restaurant createRestaurant();
    Map<Integer, Restaurant> findAllRestaurants();
    Restaurant updateRestaurant();
    Boolean removeRestaurant();
}
