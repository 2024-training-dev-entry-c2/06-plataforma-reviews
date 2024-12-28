package org.example.services.review;

import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ListRestaurantReview implements ICommand<List<Review>> {
	private final SelectRestaurant selectRestaurant;

	public ListRestaurantReview(SelectRestaurant selectRestaurant) {
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public List<Review> execute() {
		Restaurant restaurant = selectRestaurant.execute();

		return restaurant.getReviews();
	}
}