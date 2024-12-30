package org.example.reviews.services.reviews;

import org.example.reviews.models.Restaurant;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.util.List;

public class FindAllRestaurantReviewsImpl implements ICommand<List<RestaurantReview>> {
    private RestaurantRepository restaurantRepository;
    private ConsoleUtil console;

    public FindAllRestaurantReviewsImpl(ConsoleUtil console){
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.console = console;
    }
    @Override
    public List<RestaurantReview> execute() {
       Restaurant restaurant = restaurantRepository.findRestaurantByName(console.readLine("Introduzca el nombre del restaurante: "));
       console.writeLine("Calificacion del restaurante segun sus comentarios: " + restaurant.calculateAverageRating());

        return restaurant.getReviews();
    }
}
