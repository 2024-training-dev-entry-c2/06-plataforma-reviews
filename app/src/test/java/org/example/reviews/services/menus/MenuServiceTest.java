package org.example.reviews.services.menus;

import org.example.reviews.models.Menu;
import org.example.reviews.utils.ConsoleUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuServiceTest {

    private ConsoleUtil mockConsole;
    private CreateMenuImpl mockCreateMenuImpl;
    private FindMenusImpl mockFindMenusImpl;
    private MenuService menuService;

    @BeforeEach
    void setUp() {
        mockConsole = mock(ConsoleUtil.class);
        mockCreateMenuImpl = mock(CreateMenuImpl.class);
        mockFindMenusImpl = mock(FindMenusImpl.class);

        menuService = new MenuService(mockConsole);
        menuService.createMenuImpl = mockCreateMenuImpl;
        menuService.findMenusImpl = mockFindMenusImpl;
    }

    @Test
    @DisplayName("Should create a menu successfully")
    void testCreateMenu() {
        Menu mockMenu = mock(Menu.class);
        when(mockCreateMenuImpl.execute()).thenReturn(mockMenu);

        Menu result = menuService.createMenu();

        assertNotNull(result);
        assertEquals(mockMenu, result);
        verify(mockCreateMenuImpl).execute();
    }

    @Test
    @DisplayName("Should display menus successfully")
    void testDisplayMenus() {
        List<Menu> mockMenus = List.of(mock(Menu.class));
        when(mockFindMenusImpl.execute()).thenReturn(mockMenus);

        List<Menu> result = menuService.displayMenus();

        assertNotNull(result);
        assertEquals(mockMenus, result);
        verify(mockFindMenusImpl).execute();
    }
}
