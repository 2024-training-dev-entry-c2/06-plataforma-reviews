package org.example.services;

import org.example.models.Plate;
import org.example.repositories.PlateRepository;

import java.util.List;

public class PlateService {
  private PlateRepository plateRepository;

  // Constructor para usar el singleton por defecto
  public PlateService() {
    this.plateRepository = PlateRepository.getInstance();
  }

  // Constructor para inyectar dependencias (usado en pruebas)
  public PlateService(PlateRepository plateRepository) {
    this.plateRepository = plateRepository;
  }

  public void addPlate(String name, String description, Double price) {
    Plate plate = new Plate(name, description, price);
    plateRepository.addPlate(plate);
  }

  public List<Plate> getPlates() {
    return plateRepository.getPlates();
  }

  public void removePlate(String name) {
    plateRepository.removePlate(name);
  }
}
