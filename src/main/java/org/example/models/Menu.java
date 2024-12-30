package org.example.models;

import java.util.ArrayList;
import java.util.List;

public class Menu {
  private String name;
  private List<Plate> plates;

  public Menu(String name) {
    this.name = name;
    this.plates = new ArrayList<>();
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Plate> getPlates() {
    return plates;
  }

  public void setPlates(List<Plate> plates) {
    this.plates = plates;
  }

  public void addPlate(Plate plate) {
    this.plates.add(plate);
  }

  @Override
  public String toString() {
    return "Menu{" +
            "name='" + name + '\'' +
            ", plates=" + plates +
            '}';
  }
}
