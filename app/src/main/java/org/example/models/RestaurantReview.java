package org.example.models;

public class RestaurantReview extends Review {
	private Float serviceRating;
	private Float locationRating;
	private Float menuRating;

	public RestaurantReview(String comment, Float serviceRating, Float locationRating, Float menuRating) {
		super(comment);
		this.serviceRating = serviceRating;
		this.locationRating = locationRating;
		this.menuRating = menuRating;
	}

	public Float getServiceRating() {
		return serviceRating;
	}

	public Float getLocationRating() {
		return locationRating;
	}

	public Float getMenuRating() {
		return menuRating;
	}

	@Override
	public void calculateRating() {
		setRatingAverage((serviceRating + locationRating + menuRating) / 3);
	}

	@Override
	public String toString() {
		return """
          ────────────────────────────────────────────────────────────────────
          Comentario: '%s'
          Calificación del servicio: %.2f
          Calificación del lugar: %.2f
          Calificación del menú: %.2f
          Calificación promedio: %.2f
        """.formatted(getComment(), serviceRating, locationRating, menuRating, getRatingAverage());
	}
}