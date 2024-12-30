package org.example.repositories;

import org.example.models.Plate;

import java.util.ArrayList;
import java.util.List;

public class PlateRepository {
  private static PlateRepository instance;
  private List<Plate> plates;

  private PlateRepository() {
    this.plates = new ArrayList<>();
  }

  public static PlateRepository getInstance() {
    if (instance == null) {
      instance = new PlateRepository();
    }
    return instance;
  }

  public void addPlate(Plate plate) {
    plates.add(plate);
  }

  public List<Plate> getPlates() {
    return new ArrayList<>(plates);
  }

  public void removePlate(String name) {
    plates.removeIf(plate -> plate.getName().equalsIgnoreCase(name));
  }

  // Nuevo método para resetear el estado del singleton
  public static void reset() {
    instance = null;
  }
}
