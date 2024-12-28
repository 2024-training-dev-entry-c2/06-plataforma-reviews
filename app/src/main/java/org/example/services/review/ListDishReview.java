package org.example.services.review;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.services.interfaces.ICommand;
import org.example.services.menu.SelectDish;

import java.util.List;
import java.util.stream.Collectors;

import java.util.Collections;

public class ListDishReview implements ICommand<List<DishReview>> {
	private final SelectDish selectDish;

	public ListDishReview(SelectDish selectDish) {
		this.selectDish = selectDish;
	}

	@Override
	public List<DishReview> execute() {
		Dish dish = selectDish.execute();

		if (dish == null) {
			return Collections.emptyList();
		}

		return dish.getReviews().stream()
			.map(DishReview.class::cast)
			.collect(Collectors.toList());
	}
}