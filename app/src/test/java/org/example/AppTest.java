package org.example;

import org.example.services.utils.MenuMain;
import org.example.services.utils.ValidatorScanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;


import static org.mockito.Mockito.*;


class AppTest {
    private ValidatorScanner mockValidatorScanner;

    @BeforeEach
    void setUp() {
        // Crear mocks
        mockValidatorScanner = mock(ValidatorScanner.class);

    }
    @Test
    void testMainExecution() {

        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Calle Falsa 123");

        MenuMain menuMain = new MenuMain(mockValidatorScanner);


        menuMain.showMenu();
        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }
    @Test
    void main_shouldInvokeShowMenu() {
        ValidatorScanner mockScanner = mock(ValidatorScanner.class);
        MenuMain mockMenu = mock(MenuMain.class);

        new App() {
            public void runWithMocks() {
                MenuMain menu = mockMenu;
                menu.showMenu();
            }
        }.runWithMocks();


        verify(mockMenu, times(1)).showMenu();
    }

}