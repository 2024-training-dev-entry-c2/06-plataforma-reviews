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

  public void addReview(Review review){
    reviews.add(review);
  }

  public void displayRestaurant(){
    System.out.println(name + ", " + address);
  }

  public String getName() {
    return name;
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

  public String getAddress() {
    return address;
  }

  public boolean isAvailable() {
    return available;
  }
}
