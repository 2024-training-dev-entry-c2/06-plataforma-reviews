package org.example.reviews.services.reviews;

import org.example.reviews.factory.RestaurantReviewFactory;
import org.example.reviews.factory.creator.ReviewManager;
import org.example.reviews.models.Restaurant;
import org.example.reviews.models.RestaurantReview;
import org.example.reviews.models.Review;
import org.example.reviews.observer.NotificationSystem;
import org.example.reviews.repositories.RestaurantRepository;
import org.example.reviews.repositories.RestaurantReviewRepository;
import org.example.reviews.services.interfaces.ICommand;
import org.example.reviews.utils.ConsoleUtil;

import java.time.LocalDate;

public class CreateRestaurantReviewImpl implements ICommand<Review> {
    private RestaurantRepository restaurantRepository;
    private RestaurantReviewRepository restaurantReviewRepository;
    private ConsoleUtil console;
    private NotificationSystem notificationSystem;

    public CreateRestaurantReviewImpl(ConsoleUtil console, NotificationSystem notificationSystem) {
        this.restaurantRepository = RestaurantRepository.getInstance();
        this.restaurantReviewRepository = RestaurantReviewRepository.getINSTANCE();
        this.console = console;
        this.notificationSystem = notificationSystem;
    }
    @Override
    public Review execute() {
        Integer id = console.readInt("Introduzca el id de la opinion: ");
        Restaurant restaurant = restaurantRepository.findRestaurantByName(console.readLine("Introduzca el nombre del restaurante: "));
        String author = console.readLine("Nombre del autor: ");
        String comment = console.readLine("Comentarios: ");
        Float rating = console.readFloat("Calificacion (1 -5): ");
        LocalDate date = LocalDate.now();

        ReviewManager reviewManager = new ReviewManager(new RestaurantReviewFactory());
        Review review = reviewManager.createReview(restaurant.getId(), id, author, comment, rating, date);
        if (!restaurant.hasObserver(notificationSystem)) {
            restaurant.addObserver(notificationSystem);
        }

        restaurantReviewRepository.addReview((RestaurantReview) review);
        restaurant.addReview((RestaurantReview) review);

        return review;
    }
}
