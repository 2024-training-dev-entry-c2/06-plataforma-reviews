package org.example.models;

import org.example.models.review.Review;

import java.util.LinkedList;
import java.util.List;

public class Dish {
  String name;
  String description;
  Float price;
  List<Review> reviews;

  public Dish(String name, String description, Float price) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.reviews = new LinkedList<>();
  }

  public Dish() {
  }


  public void addReview(Review review){
    reviews.add(review);
  }

  public void displayDish(){
    System.out.println(name + "  .......  $" + price);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public List<Review> getReviews() {
    return reviews;
  }
}
