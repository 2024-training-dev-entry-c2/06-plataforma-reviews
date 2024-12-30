package org.example.models;

public class Restaurant {
  private Long id;
  private String name;
  private String phone;
  private String description;

  public Restaurant(Long id, String name, String phone, String description) {
    this.id = id;
    this.name = name;
    this.phone = phone;
    this.description = description;
  }

  @Override
  public String toString() {
    return "Restaurante: " + name + '\n' +
      "Descripción: " + description + '\n' +
      "Teléfono: " + phone;
  }

  public Long getId() {
    return id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getPhone() {
    return phone;
  }

  public String getDescription() {
    return description;
  }
}
