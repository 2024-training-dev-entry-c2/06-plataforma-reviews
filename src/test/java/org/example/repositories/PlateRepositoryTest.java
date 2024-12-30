package org.example.repositories;

import org.example.models.Plate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlateRepositoryTest {
  private PlateRepository repository;

  @BeforeEach
  void setUp() {
    PlateRepository.reset(); // Reinicia el singleton
    repository = PlateRepository.getInstance(); // Obtén una nueva instancia
  }

  @Test
  void testSingletonInstance() {
    PlateRepository anotherInstance = PlateRepository.getInstance();
    assertSame(repository, anotherInstance); // Ambas instancias deben ser iguales
  }

  @Test
  void testAddPlate() {
    Plate plate = new Plate("Plate Test", "Description Test", 10.0);
    repository.addPlate(plate);

    List<Plate> plates = repository.getPlates();
    assertEquals(1, plates.size());
    assertEquals("Plate Test", plates.get(0).getName());
  }

  @Test
  void testGetPlates() {
    Plate plate1 = new Plate("Plate 1", "Description 1", 20.0);
    Plate plate2 = new Plate("Plate 2", "Description 2", 30.0);
    repository.addPlate(plate1);
    repository.addPlate(plate2);

    List<Plate> plates = repository.getPlates();
    assertEquals(2, plates.size());
    assertEquals("Plate 1", plates.get(0).getName());
    assertEquals("Plate 2", plates.get(1).getName());
  }

  @Test
  void testRemovePlate() {
    Plate plate = new Plate("Plate Test", "Description Test", 10.0);
    repository.addPlate(plate);
    repository.removePlate("Plate Test");

    List<Plate> plates = repository.getPlates();
    assertTrue(plates.isEmpty());
  }

  @Test
  void testRemovePlateNotFound() {
    Plate plate = new Plate("Plate Test", "Description Test", 10.0);
    repository.addPlate(plate);
    repository.removePlate("Nonexistent Plate");

    List<Plate> plates = repository.getPlates();
    assertEquals(1, plates.size());
    assertEquals("Plate Test", plates.get(0).getName()); // Verifica que no se eliminó nada
  }
}
