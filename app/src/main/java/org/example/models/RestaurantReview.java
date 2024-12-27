package org.example.models;

class RestaurantReview extends Review {
	private Float serviceRating;
	private Float locationRating;
	private Float menuRating;

	public RestaurantReview() {
	}

	public RestaurantReview(Integer reviewId, Float ratingAverage, String comment, Float serviceRating, Float locationRating, Float menuRating) {
		super(reviewId, ratingAverage, comment);
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
}