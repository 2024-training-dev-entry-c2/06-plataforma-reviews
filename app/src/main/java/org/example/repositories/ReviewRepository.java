package org.example.repositories;

import org.example.models.Dish;
import org.example.models.DishReview;
import org.example.models.Restaurant;
import org.example.models.RestaurantReview;
import org.example.models.Review;
import org.example.models.ReviewFactory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReviewRepository {
  private static ReviewRepository instance;
  private RestaurantRepository restaurantRepository;
  private MenuRepository menuRepository;
  private ReviewFactory reviewFactory;
  private List<RestaurantReview> restaurantReviews;
  private List<DishReview> dishReviews;

  private ReviewRepository() {
    this.restaurantReviews = new LinkedList<>();
    this.dishReviews = new ArrayList<>();
    this.reviewFactory = new ReviewFactory();
  }

  public static synchronized ReviewRepository getInstance() {
    if (instance == null) {
      instance = new ReviewRepository();
    }
    return instance;
  }

  public void createReview(String typeOfReview, Float score, String comment, Long reviewedId) {
    Review review = this.reviewFactory.createReview(typeOfReview, score, comment, reviewedId);
    if(typeOfReview.equalsIgnoreCase("Restaurant")) {
      this.restaurantReviews.add((RestaurantReview)review);
    }
    else if(typeOfReview.equalsIgnoreCase("Dish")) {
      this.dishReviews.add((DishReview)review);
    }
  }

  public void setMenuRepository() {
    if (this.menuRepository == null) {
      this.menuRepository = MenuRepository.getInstance();
    }
  }

  public void setRestaurantRepository() {
    if (this.restaurantRepository == null) {
      this.restaurantRepository = RestaurantRepository.getInstance();
    }
  }

  public Restaurant getRestaurant(Long id) {
    setRestaurantRepository();
    return this.restaurantRepository.getRestaurant(id);
  }

  public void displayRestaurants() {
    setRestaurantRepository();
    Map<Long, Restaurant> restaurants = this.restaurantRepository.getRestaurants();
    restaurants.forEach((id, restaurant) -> System.out.println("\n" + id + ". Restaurante " + restaurant.getName()));
  }

  public Dish getDish(Long dishId) {
    setMenuRepository();
    return this.menuRepository.getDish(dishId);
  }

  public void getDishes(Long menuId) {
    setMenuRepository();
    this.menuRepository.getDishesMenu(menuId).forEach(dish -> System.out.println("\n" + dish.getId() + ". " + dish.toString()));
  }

  public List<Review> getReviewsById( String typeOfReview,Long reviewedId) {
    if(typeOfReview.equalsIgnoreCase("Restaurant")) {
      return this.restaurantReviews.stream().filter(review -> review.getReviewedId().equals(reviewedId)).collect(Collectors.toList());
    }
    else if(typeOfReview.equalsIgnoreCase("Dish")) {
      return this.dishReviews.stream().filter(review -> review.getReviewedId().equals(reviewedId)).collect(Collectors.toList());
    }
    return null;
  }

  public Float getAverageScore(List<Review> reviews) {
   if(!reviews.isEmpty()) {
     return reviews.stream().map(Review::getScore).reduce(0F, Float::sum) / reviews.size();
  }
     return 0F;
  }

  public String getReviewList(List<Review> reviews) {
    return reviews.stream().map(Review::toString).collect(Collectors.joining("\n"));
  }

}
