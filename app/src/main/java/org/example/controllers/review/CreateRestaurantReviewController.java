package org.example.controllers.review;

import org.example.controllers.interfaces.ICommandController;
import org.example.services.review.CreateRestaurantReview;

public class CreateRestaurantReviewController implements ICommandController {
	private final CreateRestaurantReview createRestaurantReview;

	public CreateRestaurantReviewController(CreateRestaurantReview createRestaurantReview) {
		this.createRestaurantReview = createRestaurantReview;
	}

	@Override
	public void execute() {
		Boolean restaurant = createRestaurantReview.execute();
		if (!restaurant) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆ ¡ERROR AL CREAR LA REVIEW DEL RESTAURANTE!  ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
				    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;32m☆  ¡REVIEW DEL RESTAURANTE CREADA CON ÉXITO!  ☆
				    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
	}
}