package org.example.services;

import org.example.models.Plate;
import org.example.repositories.PlateRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlateServiceTest {
  private PlateService plateService;
  private PlateRepository mockRepository;

  @BeforeEach
  void setUp() {
    // Crea un mock del PlateRepository
    mockRepository = mock(PlateRepository.class);

    // Usa el constructor con inyección de dependencias
    plateService = new PlateService(mockRepository);
  }

  @Test
  void testAddPlate() {
    Plate plate = new Plate("Plate Test", "Description Test", 10.0);

    plateService.addPlate(plate.getName(), plate.getDescription(), plate.getPrice());

    verify(mockRepository, times(1)).addPlate(argThat(p ->
            p.getName().equals("Plate Test") &&
                    p.getDescription().equals("Description Test") &&
                    p.getPrice().equals(10.0)
    ));
  }

  @Test
  void testGetPlates() {
    Plate plate1 = new Plate("Plate 1", "Description 1", 20.0);
    Plate plate2 = new Plate("Plate 2", "Description 2", 30.0);
    when(mockRepository.getPlates()).thenReturn(List.of(plate1, plate2));

    List<Plate> plates = plateService.getPlates();

    assertEquals(2, plates.size());
    assertEquals("Plate 1", plates.get(0).getName());
    assertEquals("Plate 2", plates.get(1).getName());
    verify(mockRepository, times(1)).getPlates();
  }

  @Test
  void testRemovePlate() {
    plateService.removePlate("Plate Test");

    verify(mockRepository, times(1)).removePlate("Plate Test");
  }
}
