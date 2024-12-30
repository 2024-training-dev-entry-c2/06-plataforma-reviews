package org.example.reviews.services.reviews;

import org.example.reviews.models.Dish;
import org.example.reviews.models.DishReview;
import org.example.reviews.repositories.DishRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.util.List;

public class FindAllDishesReviewsImpl implements ICommand<List<DishReview>> {
    private ConsoleUtil console;
    private DishRepository dishRepository;

    public FindAllDishesReviewsImpl(ConsoleUtil console) {
        this.console = console;
        this.dishRepository = DishRepository.getInstance();
    }
    @Override
    public List<DishReview> execute() {
        Dish dish = dishRepository.findDishByName(console.readLine("Introduzca el nombre del plato: "));
        console.writeLine("Calificacion del plato segun sus comentarios: " + dish.calculateAverageRating());
        return dish.getReviews();
    }
}
