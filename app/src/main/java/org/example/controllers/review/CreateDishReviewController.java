package org.example.controllers.review;

import org.example.controllers.interfaces.ICommandController;
import org.example.services.review.CreateDishReview;

public class CreateDishReviewController implements ICommandController {
	private final CreateDishReview createDishReview;

	public CreateDishReviewController(CreateDishReview createDishReview) {
		this.createDishReview = createDishReview;
	}

	@Override
	public void execute() {
		Boolean dish = createDishReview.execute();
		if (!dish) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆    ¡ERROR AL CREAR LA REVIEW DEL PLATO!     ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆     ¡REVIEW DEL PLATO CREADA CON ÉXITO!     ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");
	}
}