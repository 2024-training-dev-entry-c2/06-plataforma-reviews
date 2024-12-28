package org.example.services.review;

import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.models.ReviewFactory;
import org.example.services.interfaces.ICommand;
import org.example.services.restaurant.SelectRestaurant;
import org.example.utils.Validator;

public class CreateRestaurantReview implements ICommand<Boolean> {
	private final Validator validator;
	private final SelectRestaurant selectRestaurant;

	public CreateRestaurantReview(Validator validator, SelectRestaurant selectRestaurant) {
		this.validator = validator;
		this.selectRestaurant = selectRestaurant;
	}

	@Override
	public Boolean execute() {
		Restaurant restaurant = selectRestaurant.execute();

		Review review = createRestaurantReview();
		if (review == null) {
			return false;
		}

		restaurant.addReview(review);
		return true;
	}

	private Review createRestaurantReview() {
		String comment = validator.readString("Ingrese un comentario para el restaurante: ");
		Float serviceRating = readRating("servicio");
		Float locationRating = readRating("ubicación");
		Float menuRating = readRating("menú");

		return ReviewFactory.createReview(comment, serviceRating, locationRating, menuRating);
	}

	private Float readRating(String category) {
		return validator.readFloat("Ingrese una calificación para " + category + " (0-5): ");
	}
}