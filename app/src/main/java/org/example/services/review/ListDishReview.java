package org.example.services.review;

import org.example.models.Dish;
import org.example.models.Restaurant;
import org.example.models.Review;
import org.example.repositories.RestaurantRepository;
import org.example.services.interfaces.ICommand;
import org.example.services.menu.SelectDish;
import org.example.utils.Validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ListDishReview implements ICommand<List<Review>> {
	private final SelectDish selectDish;

	public ListDishReview(SelectDish selectDish) {
		this.selectDish = selectDish;
	}

	@Override
	public List<Review> execute() {
		Dish dish = selectDish.execute();

		return dish.getReviews();
	}
}