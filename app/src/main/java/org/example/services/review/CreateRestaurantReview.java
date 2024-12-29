package org.example.services.review;

import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.models.ReviewFactory;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.AddRestaurantObserver;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;

public class CreateRestaurantReview implements ICommand<Boolean> {
	private final Validator validator;
	private final SelectRestaurant selectRestaurant;
	private final AddRestaurantObserver addRestaurantObserver;

	public CreateRestaurantReview(Validator validator, SelectRestaurant selectRestaurant, AddRestaurantObserver addRestaurantObserver) {
		this.validator = validator;
		this.selectRestaurant = selectRestaurant;
		this.addRestaurantObserver = addRestaurantObserver;
	}

	@Override
	public Boolean execute() {
		Restaurant restaurant = selectRestaurant.execute();
		if (restaurant == null) {
			return false;
		}

		addRestaurantObserver.execute(restaurant);

		Review review = createRestaurantReview();

		restaurant.addReview(review);
		return true;
	}

	private Review createRestaurantReview() {
		String comment = validator.readString("Ingrese un comentario para el restaurante: ");
		Float serviceRating = validator.readRating("Ingrese una calificación para el servicio (0-5): ");
		Float locationRating = validator.readRating("Ingrese una calificación para el lugar (0-5): ");
		Float menuRating = validator.readRating("Ingrese una calificación para el menú (0-5): ");
		return ReviewFactory.createReview(comment, serviceRating, locationRating, menuRating);
	}
}