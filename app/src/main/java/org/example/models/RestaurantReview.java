package org.example.models;

public class RestaurantReview extends Review {
	private Float serviceRating;
	private Float locationRating;
	private Float menuRating;

	public RestaurantReview() {
	}

	public RestaurantReview(String comment, Float serviceRating, Float locationRating, Float menuRating) {
		super(comment);
		this.serviceRating = serviceRating;
		this.locationRating = locationRating;
		this.menuRating = menuRating;
	}

	public Float getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(Float serviceRating) {
		this.serviceRating = serviceRating;
	}

	public Float getLocationRating() {
		return locationRating;
	}

	public void setLocationRating(Float locationRating) {
		this.locationRating = locationRating;
	}

	public Float getMenuRating() {
		return menuRating;
	}

	public void setMenuRating(Float menuRating) {
		this.menuRating = menuRating;
	}

	@Override
	public void calculateRating() {
		setRatingAverage((serviceRating + locationRating + menuRating) / 3);
	}

	@Override
	public String toString() {
		return """
        RestaurantReview {
          Service Rating: %f
          Location Rating: %f
          Menu Rating: %f
        }
        """.formatted(serviceRating, locationRating, menuRating);
	}
}