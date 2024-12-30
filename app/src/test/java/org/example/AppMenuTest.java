package org.example;

import org.example.reviews.controllers.interfaces.IController;
import org.example.reviews.controllers.menu.MenuController;
import org.example.reviews.controllers.restaurant.RestaurantController;
import org.example.reviews.controllers.reviews.ReviewController;
import org.example.reviews.utils.AppMenu;
import org.example.reviews.utils.ConsoleUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;

import java.util.Map;

import static org.mockito.Mockito.*;

class AppMenuTest {

    private RestaurantController mockRestaurantController;
    private MenuController mockMenuController;
    private ReviewController mockReviewController;
    private ConsoleUtil mockConsole;
    private AppMenu appMenu;

    @BeforeEach
    void setUp() {
        mockRestaurantController = Mockito.mock(RestaurantController.class);
        mockMenuController = Mockito.mock(MenuController.class);
        mockReviewController = Mockito.mock(ReviewController.class);
        mockConsole = Mockito.mock(ConsoleUtil.class);

        appMenu = new AppMenu(mockRestaurantController, mockMenuController, mockReviewController,() -> {}, mockConsole);
    }

    @Test
    @DisplayName("test main menu method")
    void testShowMenu() {
        appMenu.showMenu();

        verify(mockConsole).writeLine("Bienvenido a la app Restaurant Reviews\n");
        verify(mockConsole).writeLine("1. Gestion de restaurantes");
        verify(mockConsole).writeLine("2. Gestion de menus");
        verify(mockConsole).writeLine("3. Gestion de reviews");
        verify(mockConsole).writeLine("4. Salir");
    }

    @Test
    @DisplayName("test main menu controllers")
    void testExecuteControllerValidOption() {
        Map<Integer, IController> controllers = Map.of(
                1, mockRestaurantController,
                2, mockMenuController,
                3, mockReviewController
        );

        appMenu.executeController(1, controllers);
        verify(mockRestaurantController).execute();

        appMenu.executeController(2, controllers);
        verify(mockMenuController).execute();

        appMenu.executeController(3, controllers);
        verify(mockReviewController).execute();
    }

    @Test
    @DisplayName("test main menu invalid option")
    void testExecuteControllerInvalidOption() {
        Map<Integer, IController> controllers = Map.of(
                1, mockRestaurantController,
                2, mockMenuController,
                3, mockReviewController
        );

        appMenu.executeController(99, controllers);
        verifyNoInteractions(mockRestaurantController, mockMenuController, mockReviewController);
    }

    @Test
    @DisplayName("test main menu selections")
    void testExecuteFlow() {
        when(mockConsole.readInt(anyString()))
                .thenReturn(1)
                .thenReturn(4);

        doNothing().when(mockRestaurantController).execute();

        appMenu.execute();

        verify(mockConsole, times(2)).readInt("Seleccione una opcion: ");
        verify(mockRestaurantController).execute();
        verify(mockConsole, times(11)).writeLine(anyString());

        verify(mockConsole).writeLine("Gracias por usar la app");
    }


    @Test
    @DisplayName("test app exit")
    void testExit() {
        appMenu.exit();
        verify(mockConsole).writeLine("Gracias por usar la app");
    }

}
