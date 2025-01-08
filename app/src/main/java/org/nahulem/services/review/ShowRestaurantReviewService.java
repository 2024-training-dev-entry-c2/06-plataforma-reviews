package org.nahulem.services.review;

import org.nahulem.models.Restaurant;
import org.nahulem.models.RestaurantReview;
import org.nahulem.services.interfaces.ICommand;
import org.nahulem.services.restaurant.SelectRestaurantService;

import java.util.List;

public class ShowRestaurantReviewService implements ICommand<List<RestaurantReview>> {
    private final SelectRestaurantService selectRestaurantService;

    public ShowRestaurantReviewService(SelectRestaurantService selectRestaurantService) {
        this.selectRestaurantService = selectRestaurantService;
    }

    @Override
    public List<RestaurantReview> execute() {
        Restaurant restaurant = selectRestaurantService.execute();

        if (restaurant == null) {
            return List.of();
        }

        return restaurant.getReviews().stream().map(RestaurantReview.class::cast).toList();
    }
}
