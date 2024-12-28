package org.example.services.review;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.services.interfaces.ICommand;
import org.example.services.menu.SelectDish;

import java.util.List;
import java.util.stream.Collectors;

public class ListDishReview implements ICommand<List<DishReview>> {
	private final SelectDish selectDish;

	public ListDishReview(SelectDish selectDish) {
		this.selectDish = selectDish;
	}

	@Override
	public List<DishReview> execute() {
		Dish dish = selectDish.execute();

		return dish.getReviews().stream()
			.map(DishReview.class::cast)
			.collect(Collectors.toList());
	}
}