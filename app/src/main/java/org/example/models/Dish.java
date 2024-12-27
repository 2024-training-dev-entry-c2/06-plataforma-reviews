package org.example.models;

import java.util.List;

class Dish {
  private Integer dishId;
  private String name;
  private String description;
  private Float price;
  private List<Review> reviews;

  public Dish() {
  }

  public Dish(Integer dishId, String name, String description, Float price, List<Review> reviews) {
    this.dishId = dishId;
    this.name = name;
    this.description = description;
    this.price = price;
    this.reviews = reviews;
  }

  public Integer getDishId() {
    return dishId;
  }

  public void setDishId(Integer dishId) {
    this.dishId = dishId;
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

  public Float getPrice() {
    return price;
  }

  public void setPrice(Float price) {
    this.price = price;
  }

  public List<Review> getReviews() {
    return reviews;
  }

  public void setReviews(List<Review> reviews) {
    this.reviews = reviews;
  }

  public Float calculateAverageRating() {
    return (float) reviews.stream()
      .mapToDouble(Review::getRatingAverage)
      .average()
      .orElse(0.0);
  }
}