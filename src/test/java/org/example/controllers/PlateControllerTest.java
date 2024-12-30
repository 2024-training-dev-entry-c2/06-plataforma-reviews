package org.example.controllers;

import org.example.models.Plate;
import org.example.services.PlateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class PlateControllerTest {
  private PlateController controller;
  private PlateService service;

  @BeforeEach
  void setUp() {
    service = mock(PlateService.class);
    controller = new PlateController(service);
  }

  @Test
  void testAddPlate() {
    controller.addPlate("Plato Test", "Descripcion Test", 100.0);
    verify(service).addPlate("Plato Test", "Descripcion Test", 100.0);
  }

  @Test
  void testGetPlatesEmpty() {
    when(service.getPlates()).thenReturn(List.of());
    controller.getPlates();
    verify(service).getPlates();
  }

  @Test
  void testGetPlatesWithData() {
    when(service.getPlates()).thenReturn(List.of(
            new Plate("Plato Test 1", "Descripcion 1", 100.0)
    ));
    controller.getPlates();
    verify(service).getPlates();
  }

  @Test
  void testRemovePlate() {
    controller.removePlate("Plato Test");
    verify(service).removePlate("Plato Test");
  }
}
