package org.example.controllers.review;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.RestaurantReview;
import org.example.services.review.ListRestaurantReview;

import java.util.List;

public class ListRestaurantReviewController implements ICommandController {
	private final ListRestaurantReview listRestaurantReview;

	public ListRestaurantReviewController(ListRestaurantReview listRestaurantReview) {
		this.listRestaurantReview = listRestaurantReview;
	}

	@Override
	public void execute() {
		List<RestaurantReview> restaurants = listRestaurantReview.execute();
		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆      LISTADO DE REVIEWS DEL RESTAURANTE     ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");

		restaurants.forEach(review -> System.out.println(review.toString()));
	}
}