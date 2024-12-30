package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Plate extends Observable {
  private String name;
  private String description;
  private Double price;
  private Double averageRating;
  private List<ReviewPlate> reviews;

  public Plate(String name, String description, Double price) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.averageRating = 0.0;
    this.reviews = new ArrayList<>();
  }

  public void addReview(ReviewPlate review){
    reviews.add(review);
    updateAverageRating();
    notifyObservers("Se ha agregado una reseña a este plato");
  }

  private void updateAverageRating(){
    Double total = (double) reviews.stream().mapToInt(ReviewPlate::getRating).sum();
    this.averageRating = total / reviews.size();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public Double getPrice() {
    return price;
  }

  public void setPrice(Double price) {
    this.price = price;
  }

  public Double getAverageRating() {
    return averageRating;
  }

  public void setAverageRating(Double averageRating) {
    this.averageRating = averageRating;
  }

  public List<ReviewPlate> getReviews() {
    return reviews;
  }

  @Override
  public String toString() {
    return "Plate{" +
            "name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", price=" + price +
            ", averageRating=" + averageRating +
            '}';
  }
}
