package org.example.models.review;

import org.example.models.Dish;

public class DishReview extends Review {
  private Dish dish;
  private Float taste;
  private Float presentation;

  public DishReview(String comment, Dish dish, Float taste, Float presentation) {
    super(comment, taste + presentation / 2f);
    this.dish = dish;
    this.taste = taste;
    this.presentation = presentation;
  }

  public DishReview() {
  }

  @Override
  public void displayReview() {
    System.out.println("\n-------------- Reseña --------------");
    System.out.println("Plato: " + dish.getName());
    System.out.println("Calificación general: " + getRating());
    System.out.println("Sabor: " + taste);
    System.out.println("Presentación: " + presentation);
    System.out.println("Comentario: " + getComment());
    System.out.println("------------------------------------");
  }


  public Float getTaste() {
    return taste;
  }

  public Float getPresentation() {
    return presentation;
  }

  public Dish getDish() {
    return dish;
  }
}
