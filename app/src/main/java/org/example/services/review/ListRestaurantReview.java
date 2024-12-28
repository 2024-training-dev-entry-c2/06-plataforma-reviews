package org.example.services.review;

import org.example.models.Restaurant;
import org.example.models.RestaurantReview;
import org.example.models.Review;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;

import java.util.List;
import java.util.stream.Collectors;

public class ListRestaurantReview implements ICommand<List<RestaurantReview>> {
	private final SelectRestaurant selectRestaurant;

	public ListRestaurantReview(SelectRestaurant selectRestaurant) {
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public List<RestaurantReview> execute() {
		Restaurant restaurant = selectRestaurant.execute();

		return restaurant.getReviews().stream()
			.map(RestaurantReview.class::cast)
			.collect(Collectors.toList());
	}
}