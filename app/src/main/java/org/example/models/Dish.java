package org.example.models;

public class Dish {
  private Long id;
  private Long menuId;
  private String name;
  private String description;
  private Long price;

  public Dish(Long id, Long menuId, String name, String description, Long price) {
    this.id = id;
    this.menuId = menuId;
    this.name = name;
    this.description = description;
    this.price = price;
  }

  @Override
  public String toString() {
    return "Plato: " + name + '\n' +
      "Descripción: " + description + '\n' +
      "Precio: " + price;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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

  public Long getPrice() {
    return price;
  }

  public void setPrice(Long price) {
    this.price = price;
  }
}
