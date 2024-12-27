package org.example.models;

import java.util.List;

public class Restaurant {
	private Integer restaurantId;
	private String name;
	private String description;
	private String location;
	private Menu menu;
	private List<Review> reviews;

	public Restaurant() {
	}

	public Restaurant(Integer restaurantId, String name, String description, String location, Menu menu, List<Review> reviews) {
		this.restaurantId = restaurantId;
		this.name = name;
		this.description = description;
		this.location = location;
		this.menu = menu;
		this.reviews = reviews;
	}

	public Integer getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Integer restaurantId) {
		this.restaurantId = restaurantId;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
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

	public void addMenu(Menu menu) {
		this.menu = menu;
	}

	public void addReview(Review review) {
		this.reviews.add(review);
	}

	public Float calculateAverageRating() {
		return (float) reviews.stream()
			.mapToDouble(Review::getRatingAverage)
			.average()
			.orElse(0.0);
	}
}