package org.example.controllers;

import org.example.models.Menu;
import org.example.models.Plate;
import org.example.services.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;

class MenuControllerTest {
  private MenuController controller;
  private MenuService service;

  @BeforeEach
  void setUp() {
    service = mock(MenuService.class); // Crear un mock de MenuService
    controller = new MenuController(); // Crear instancia del controlador
    controller.setMenuService(service); // Inyectar el mock usando el setter
  }

  @Test
  void testAddMenu() {
    controller.addMenu("Menu Test");
    verify(service).addMenu("Menu Test"); // Verificar que el servicio fue llamado correctamente
  }

  @Test
  void testGetMenusEmpty() {
    when(service.getMenus()).thenReturn(List.of()); // Simular lista vacía
    controller.getMenus();
    verify(service).getMenus(); // Verificar que se llamó a getMenus
  }

  @Test
  void testGetMenusWithData() {
    when(service.getMenus()).thenReturn(List.of(
            new Menu("Menu 1"),
            new Menu("Menu 2")
    )); // Simular lista con datos
    controller.getMenus();
    verify(service).getMenus(); // Verificar que se llamó a getMenus
  }

  @Test
  void testRemoveMenu() {
    controller.removeMenu("Menu Test");
    verify(service).removeMenu("Menu Test"); // Verificar que se llamó a removeMenu
  }

  @Test
  void testAddPlateToMenu() {
    Plate plate = new Plate("Plato Test", "Descripción", 50.0);
    controller.addPlateToMenu("Menu Test", plate);
    verify(service).addPlateToMenu("Menu Test", plate); // Verificar que se llamó a addPlateToMenu
  }
}