package org.example.controllers.review;

import org.example.controllers.interfaces.ICommandController;
import org.example.models.DishReview;
import org.example.services.review.ListDishReview;

import java.util.List;

public class ListDishReviewController implements ICommandController {
	private final ListDishReview listDishReview;

	public ListDishReviewController(ListDishReview listDishReview) {
		this.listDishReview = listDishReview;
	}

	@Override
	public void execute() {
		List<DishReview> dishes = listDishReview.execute();
		if (dishes.isEmpty()) {
			System.out.println("""
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[1;31m☆      NO HAY RESEÑAS DISPONIBLES     ☆
				    \033[1;31m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
				    \033[0m
				""");
			return;
		}

		System.out.println("""
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[1;32m☆        LISTADO DE RESEÑAS DEL PLATO         ☆
			    \033[1;32m☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆☆
			    \033[0m
			""");

		dishes.forEach(review -> System.out.println(review.toString()));
	}
}