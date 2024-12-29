package org.example.models;

import org.example.models.review.Review;

import java.util.LinkedList;
import java.util.List;

public class Restaurant {
  private String name;
  private String address;
  private Boolean available;
  private Menu menu;
  private List<Review> reviews;

  public Restaurant(String name, String address, Boolean available) {
    this.name = name;
    this.address = address;
    this.available = available;
    this.reviews = new LinkedList<>();
  }

  public Restaurant() {
  }

  public Float getAverageRanking() {
    if (reviews == null) {
      return 0.0f;
    }

    return (float) reviews.stream()
      .mapToDouble(Review::getRating)
      .average()
      .orElse(0.0);
  }

  public void addReview(Review review){
    reviews.add(review);
  }

  public void displayRestaurant(){
    System.out.println(name + ", " + address);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable(Boolean available) {
    this.available = available;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Menu getMenu() {
    return menu;
  }

  public void setMenu(Menu menu) {
    this.menu = menu;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }
}
