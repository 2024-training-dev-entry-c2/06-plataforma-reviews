package org.example;

import org.example.models.Menu;
import org.example.repositories.MenuRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

class MenuRepositoryTest {

  private MenuRepository menuRepository;

  @Mock
  private Menu menuMock;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    menuRepository = MenuRepository.getInstance();
    menuRepository.setRestaurantRepository();
    when(menuMock.getDishIds()).thenReturn(new HashSet<>());
  }

  @Test
  void testAddMenu() {
    Long menuId = 1L;

    menuRepository.addMenu(menuId);

    Menu menu = menuRepository.getMenu(menuId);
    assertNotNull(menu);
    assertEquals(menuId, menu.getId());
  }


  @Test
  void testRemoveMenu() {
    Long menuId = 1L;

    menuRepository.addMenu(menuId);
    menuRepository.addDishMenu(menuId, "Plato1", "Descripción del plato1", 200L);

    menuRepository.removeMenu(menuId);
    assertNull(menuRepository.getMenu(menuId));
  }

}

