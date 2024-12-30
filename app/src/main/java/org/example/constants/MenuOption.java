package org.example.constants;

import java.util.Objects;

public enum MenuOption {
  CREATE_DISH(1, "Crear Plato"),
  CREATE_RESTAURANT(2, "Crear Restaurante"),
  ADD_DISH_TO_MENU(3, "Agregar Plato a Menú"),
  ADD_REVIEW_TO_DISH(4, "Agregar Reseña a Plato"),
  ADD_REVIEW_TO_RESTAURANT(5, "Agregar Reseña a Restaurante"),
  VIEW_ALL_DISHES_MENU(6, "Ver Todos los Platos de un Menú"),
  VIEW_ALL_RESTAURANTS(7, "Ver Todos los Restaurantes"),
  VIEW_DISH_REVIEWS(8, "Ver Reseñas de Platos"),
  VIEW_RESTAURANT_REVIEWS(9, "Ver Reseñas de Restaurantes"),
  EDIT_RESTAURANT(10, "Editar Restaurante"),
  DELETE_RESTAURANT(11, "Eliminar Restaurante"),
  ASOCIATE_MENU_TO_RESTAURANT(12, "Asociar Menú a Restaurante"),
  EDIT_DISH(13, "Editar Plato"),
  DELETE_DISH(14, "Eliminar Plato"),
  AVERAGE_RATING_OF_DISH(15, "Calificación Promedio de Plato"),
  AVERAGE_RATING_OF_RESTAURANT(16, "Calificación Promedio de Restaurante"),
  EXIT(17, "Salir");

  private final Integer optionNumber;
  private final String description;

  MenuOption(Integer optionNumber, String description) {
    this.optionNumber = optionNumber;
    this.description = description;
  }

  public Integer getOptionNumber() {
    return optionNumber;
  }

  public String getDescription() {
    return description;
  }

  public static MenuOption fromInt(Integer option) {
    for (MenuOption menuOption : values()) {
      if (Objects.equals(menuOption.optionNumber, option)) {
        return menuOption;
      }
    }
    throw new IllegalArgumentException("Opcion no encontrada");
  }
}