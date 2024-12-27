package org.example.models;

import org.example.models.interfaces.IObservable;
import org.example.models.interfaces.IObserver;

import java.util.ArrayList;
import java.util.List;

public class Restaurant implements IObservable {
	private static int idCounter = 0;
	private Integer restaurantId;
	private String name;
	private String description;
	private String location;
	private Menu menu;
	private List<Review> reviews;

	private List<IObserver> observers = new ArrayList<>();

	public Restaurant() {
	}

	public Restaurant(String name, String description, String location, Menu menu, List<Review> reviews) {
		this.restaurantId = generateId();
		this.name = name;
		this.description = description;
		this.location = location;
		this.menu = menu;
		this.reviews = reviews;
	}

	private static int generateId() {
		return idCounter++;
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

		notifyObservers("Se ha agregado una nueva review al restaurante: " + this.name);

		Float newAverage = calculateAverageRating();
		notifyObservers("La calificación promedio de " + this.name + " ha cambiado a: " + newAverage);
	}

	public Float calculateAverageRating() {
		return (float) reviews.stream()
			.mapToDouble(Review::getRatingAverage)
			.average()
			.orElse(0.0);
	}

	@Override
	public void addObserver(IObserver observer) {
		observers.add(observer);
	}

	@Override
	public void removeObserver(IObserver observer) {
		observers.remove(observer);
	}

	@Override
	public void notifyObservers(String message) {
		observers.forEach(observer -> observer.update(message));
	}
}