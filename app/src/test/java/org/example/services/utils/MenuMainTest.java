package org.example.services.utils;

import org.example.factory.ReviewFactory;
import org.example.models.DishFood;
import org.example.models.IReview;
import org.example.models.Restaurant;
import org.example.models.User;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MenuMainTest {
    private IValidatorScanner mockValidatorScanner;
    private RestaurantRepository mockRestaurantRepository;
    private MenuRepository mockMenuRepository;
    private MenuMain menuMain;

    @BeforeEach
    void setUp() {
        // Crear mocks
        mockValidatorScanner = mock(IValidatorScanner.class);
        mockRestaurantRepository = mock(RestaurantRepository.class);
        mockMenuRepository = mock(MenuRepository.class);
        menuMain = new MenuMain(mockValidatorScanner);
    }

    @Test
    void testRestaurantCreation() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Calle Falsa 123");

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
    void removeRestaurant() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(2, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");

        menuMain.showMenu();
        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void showRestaurant() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(3, 4);

        menuMain.showMenu();
        verify(mockValidatorScanner, times(2)).integerScanner("Ingrese un Valor : ");
        verify(mockValidatorScanner, times(2)).integerScanner("Selecciona una opción:");
    }

    @Test
    void addDishFood() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");
        when(mockValidatorScanner.stringScanner("Escribe el nombre del plato:"))
                .thenReturn("plato Prueba");
        when(mockValidatorScanner.stringScanner("Escribe una descripcion:"))
                .thenReturn("plato description");
        when(mockValidatorScanner.doubleScanner("Escribe el precio:"))
                .thenReturn(2000000.0);

        menuMain.showMenu();
        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del plato:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe una descripcion:");
        inOrder.verify(mockValidatorScanner).doubleScanner("Escribe el precio:");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void removeDishFood() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(2, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");
        when(mockValidatorScanner.integerScanner("Escribe el numero del plato"))
                .thenReturn(0);

        menuMain.showMenu();
        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("Escribe el numero del plato");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void showDishFood() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(3, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");

        menuMain.showMenu();
        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");

    }

    @Test
    void addReviewRestaurant() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(3, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Prueba");
        when(mockValidatorScanner.stringScanner("Ingrese su nombre"))
                .thenReturn("Usuario prueba");
        when(mockValidatorScanner.stringScanner("Ingrese su comentario"))
                .thenReturn("Usuario prueba");
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar"))
                .thenReturn(2.5f);
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu"))
                .thenReturn(2.5f);
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del serviceRating"))
                .thenReturn(2.5f);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Ingrese su nombre");
        inOrder.verify(mockValidatorScanner).stringScanner("Ingrese su comentario");
        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del del lugar");
        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del del menu");
        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del del serviceRating");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void addReviewDishFood() {
        // Configuración de los mocks
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : ")).thenReturn(3, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:")).thenReturn(2, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante")).thenReturn("Restaurante Prueba");
        when(mockValidatorScanner.integerScanner("seleccione un plato")).thenReturn(1);
        when(mockValidatorScanner.stringScanner("Ingrese su comentario")).thenReturn("prueba");
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del sabor")).thenReturn(2.5f);
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del presentacion")).thenReturn(2.5f);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("seleccione un plato");
        inOrder.verify(mockValidatorScanner).stringScanner("Ingrese su comentario"); // Ensure this is called correctly
        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del sabor");
        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del presentacion");
    }

    @Test
    void showReviewRestaurant() {
        // Configuración de los mocks
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : ")).thenReturn(3, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:")).thenReturn(3, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante")).thenReturn("Restaurante Prueba");

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");

    }
    void showReviewDishFood() {
        // Configuración de los mocks
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : ")).thenReturn(3, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:")).thenReturn(4, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante")).thenReturn("Restaurante Prueba");

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");

    }


}