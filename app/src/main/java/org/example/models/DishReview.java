package org.example.models;

public class DishReview extends Review {
  private Float tasteRating;
  private Float presentationRating;

  public DishReview(String comment, Float tasteRating, Float presentationRating) {
    super(comment);
    this.tasteRating = tasteRating;
    this.presentationRating = presentationRating;
  }

  public Float getTasteRating() {
    return tasteRating;
  }

  public Float getPresentationRating() {
    return presentationRating;
  }

  @Override
  public void calculateRating() {
    setRatingAverage((tasteRating + presentationRating) / 2);
  }

  @Override
  public String toString() {
    return """
          ────────────────────────────────────────────────────────────────────
          Comentario: '%s'
          Calificación de la sabor: %.2f
          Calificación de la presentación: %.2f
          Calificación promedio: %.2f
        """.formatted(getComment(), tasteRating, presentationRating, getRatingAverage());
  }
}