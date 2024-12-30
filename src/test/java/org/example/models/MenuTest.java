package org.example.models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {
  private Menu menu;

  @BeforeEach
  void setUp() {
    menu = new Menu("Menu Test");
  }

  @Test
  void testConstructor() {
    assertEquals("Menu Test", menu.getName());
    assertTrue(menu.getPlates().isEmpty());
  }

  @Test
  void testSetName() {
    menu.setName("Updated Menu");
    assertEquals("Updated Menu", menu.getName());
  }

  @Test
  void testSetPlates() {
    Plate plate1 = new Plate("Plate 1", "Description 1", 10.0);
    Plate plate2 = new Plate("Plate 2", "Description 2", 20.0);
    menu.setPlates(List.of(plate1, plate2));

    assertEquals(2, menu.getPlates().size());
    assertEquals("Plate 1", menu.getPlates().get(0).getName());
  }

  @Test
  void testAddPlate() {
    Plate plate = new Plate("Plate Test", "Description Test", 15.0);
    menu.addPlate(plate);

    assertEquals(1, menu.getPlates().size());
    assertEquals("Plate Test", menu.getPlates().get(0).getName());
  }

  @Test
  void testToString() {
    Plate plate = new Plate("Plate Test", "Description Test", 15.0);
    menu.addPlate(plate);

    String expected = "Menu{name='Menu Test', plates=[Plate{name='Plate Test', description='Description Test', price=15.0, averageRating=0.0}]}";
    assertEquals(expected, menu.toString());
  }
}
