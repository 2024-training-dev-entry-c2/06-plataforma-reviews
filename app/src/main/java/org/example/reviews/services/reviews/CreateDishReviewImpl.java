package org.example.reviews.services.reviews;


import org.example.reviews.factory.DishReviewFactory;
import org.example.reviews.factory.creator.ReviewManager;

import org.example.reviews.models.Dish;
import org.example.reviews.models.DishReview;
import org.example.reviews.models.Review;
import org.example.reviews.observer.NotificationSystem;
import org.example.reviews.repositories.DishRepository;
import org.example.reviews.repositories.DishReviewRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.time.LocalDate;

public class CreateDishReviewImpl implements ICommand<Review> {
    private DishRepository dishRepository;
    private DishReviewRepository dishReviewRepository;
    private ConsoleUtil console;

    public CreateDishReviewImpl(ConsoleUtil console) {
        this.console = console;
        this.dishRepository = DishRepository.getInstance();
        this.dishReviewRepository = DishReviewRepository.getINSTANCE();
    }
    @Override
    public Review execute() {
        Integer id = console.readInt("Introduzca el id de la opinion: ");
        Dish dish = dishRepository.findDishByName(console.readLine("Introduzca el nombre del plato: "));
        String author = console.readLine("Nombre del autor: ");
        String comment = console.readLine("Comentarios: ");
        Float rating = console.readFloat("Calificacion: ");
        LocalDate date = LocalDate.now();

        ReviewManager reviewManager = new ReviewManager(new DishReviewFactory());
        Review review = reviewManager.createReview(dish.getId(), id, author, comment, rating, date);
        NotificationSystem notificationSystem = new NotificationSystem();
        dish.addObserver(notificationSystem);
        dishReviewRepository.addReview((DishReview) review);
        dish.addReview((DishReview) review);

        return review;
    }
}
