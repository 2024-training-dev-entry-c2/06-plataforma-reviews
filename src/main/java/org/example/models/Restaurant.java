package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Restaurant extends Observable {
  private String name;
  private String address;
  private Double averageRating;
  private List<ReviewRestaurant> reviews;

  public Restaurant(String name, String address) {
    this.name = name;
    this.address = address;
    this.averageRating = 0.0;
    this.reviews = new ArrayList<>();
  }

  public void addReview(ReviewRestaurant review){
    reviews.add(review);
    updateAverageRating();
    notifyObservers("Se ha agregado una reseña a este restaurante");
  }

  private void updateAverageRating(){
    Double total = (double) reviews.stream().mapToInt(ReviewRestaurant::getRating).sum();
    this.averageRating = total / reviews.size();
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

  public void setAddress(String address) {
    this.address = address;
  }

  public Double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(Double averageRating) {
    this.averageRating = averageRating;
  }

  public List<ReviewRestaurant> getReviews() {
    return reviews;
  }

  @Override
  public String toString() {
    return "Restaurant{" +
            "name='" + name + '\'' +
            ", address='" + address + '\'' +
            ", averageRating=" + averageRating +
            '}';
  }
}
