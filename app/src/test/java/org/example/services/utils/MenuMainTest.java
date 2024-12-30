package org.example.services.utils;


import org.example.repositories.DishRepository;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InOrder;


import static org.mockito.Mockito.*;

class MenuMainTest {
    private ValidatorScanner mockValidatorScanner;
    private RestaurantRepository mockRestaurantRepository;
    private MenuRepository mockMenuRepository;
    private DishRepository mockDishRepository;
    private MenuMain menuMain;

    @BeforeEach
    void setUp() {
        // Crear mocks
        mockValidatorScanner = mock(ValidatorScanner.class);
        mockRestaurantRepository = mock(RestaurantRepository.class);
        mockMenuRepository = mock(MenuRepository.class);
        mockDishRepository = mock(DishRepository.class);
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


    //option no valida por menu
    @Test
    void mainMenuRestaurantNoValue() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(6, 4);


        menuMain.showMenu();
        verify(mockValidatorScanner, times(2)).integerScanner("Ingrese un Valor : ");
        verify(mockValidatorScanner, times(2)).integerScanner("Selecciona una opción:");

    }

    @Test
    void mainMenuDishfooodNoValue() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(6, 4);


        menuMain.showMenu();
        verify(mockValidatorScanner, times(2)).integerScanner("Ingrese un Valor : ");
        verify(mockValidatorScanner, times(2)).integerScanner("Selecciona una opción:");

    }

    @Test
    void mainMenuReviewtNoValue() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(3, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(6, 5);


        menuMain.showMenu();
        verify(mockValidatorScanner, times(2)).integerScanner("Ingrese un Valor : ");
        verify(mockValidatorScanner, times(2)).integerScanner("Selecciona una opción:");

    }

    @Test
    void mainMenuNoValue(){
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(7, 0);
        menuMain.showMenu();
        verify(mockValidatorScanner, times(2)).integerScanner("Ingrese un Valor : ");
    }

    /// casos especiales complejos
    @Test
    void createAndRemoveRestaurant() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 2, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Nuevo");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Avenida Principal 456");

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void createRestaurantAndAddDish() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4, 1, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Gourmet");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Calle Gastronómica 789");

        when(mockValidatorScanner.stringScanner("Escribe el nombre del plato:"))
                .thenReturn("Plato Estrella");
        when(mockValidatorScanner.stringScanner("Escribe una descripcion:"))
                .thenReturn("Delicioso plato estrella del restaurante.");
        when(mockValidatorScanner.doubleScanner("Escribe el precio:"))
                .thenReturn(50000.0);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del plato:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe una descripcion:");
        inOrder.verify(mockValidatorScanner).doubleScanner("Escribe el precio:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void createRestaurantAddDishAndShowDishes() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 2, 2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4, 1, 3, 4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Familiar");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Avenida Familiar 321");

        when(mockValidatorScanner.stringScanner("Escribe el nombre del plato:"))
                .thenReturn("Plato Infantil");
        when(mockValidatorScanner.stringScanner("Escribe una descripcion:"))
                .thenReturn("Plato especial para niños.");
        when(mockValidatorScanner.doubleScanner("Escribe el precio:"))
                .thenReturn(20000.0);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del plato:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe una descripcion:");
        inOrder.verify(mockValidatorScanner).doubleScanner("Escribe el precio:");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void createRestaurantAddDishAndAddDishReview() {

        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 2, 3, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4, 1, 4, 2, 3, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Elegante");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Avenida Gourmet 123");
        when(mockValidatorScanner.stringScanner("Escribe el nombre del plato:"))
                .thenReturn("Plato Gourmet");
        when(mockValidatorScanner.stringScanner("Escribe una descripcion:"))
                .thenReturn("Un plato sofisticado para paladares exigentes.");
        when(mockValidatorScanner.doubleScanner("Escribe el precio:"))
                .thenReturn(75000.0);
        when(mockValidatorScanner.integerScanner("seleccione un plato")).thenReturn(0);

        when(mockValidatorScanner.integerScanner("seleccione un plato para ver el review")).thenReturn(1);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);

        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del plato:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe una descripcion:");
        inOrder.verify(mockValidatorScanner).doubleScanner("Escribe el precio:");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).integerScanner("seleccione un plato");
//        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del sabor");
//        inOrder.verify(mockValidatorScanner).floatScanner("ingrese de 0 a 5 la calificacion del presentacion");

        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }

    @Test
    void createRestaurantAndListRestaurants() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 3,4);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante El Buen Sabor");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Calle Sabor 100");

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");


    }

    @Test
    void testRestaurantCreationTwoTimes() {
        // Simular entradas del usuario
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 1,4);

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
    void createRestaurantAndAddDishTwoTimes() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 2, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4, 1,1,4);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Gourmet");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Calle Gastronómica 789");

        when(mockValidatorScanner.stringScanner("Escribe el nombre del plato:"))
                .thenReturn("Plato Estrella");
        when(mockValidatorScanner.stringScanner("Escribe una descripcion:"))
                .thenReturn("Delicioso plato estrella del restaurante.");
        when(mockValidatorScanner.doubleScanner("Escribe el precio:"))
                .thenReturn(50000.0);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");

        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del plato:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe una descripcion:");
        inOrder.verify(mockValidatorScanner).doubleScanner("Escribe el precio:");
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
    }
    @Test
    void createRestaurantShowReviewDishes() {
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : "))
                .thenReturn(1, 3, 0);

        when(mockValidatorScanner.integerScanner("Selecciona una opción:"))
                .thenReturn(1, 4, 4, 5);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn("Restaurante Gourmet");
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn("Calle Gastronómica 789");

        when(mockValidatorScanner.integerScanner("seleccione un plato para ver el review")).thenReturn(0);

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el direccion del Restaurante");

        inOrder.verify(mockValidatorScanner).integerScanner("seleccione un plato para ver el review");
    }
    @Test
    void addReviewDishFood() {
        // Configuración de los mocks
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : ")).thenReturn(3, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:")).thenReturn(2, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante")).thenReturn("Restaurante Prueba");

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");


    }
    @Test
    void addReviewDishFoodFail() {
        // Configuración de los mocks
        when(mockValidatorScanner.integerScanner("Ingrese un Valor : ")).thenReturn(3, 0);
        when(mockValidatorScanner.integerScanner("Selecciona una opción:")).thenReturn(2, 5);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante")).thenReturn("null");

        menuMain.showMenu();

        InOrder inOrder = inOrder(mockValidatorScanner);
        inOrder.verify(mockValidatorScanner).integerScanner("Ingrese un Valor : ");
        inOrder.verify(mockValidatorScanner).integerScanner("Selecciona una opción:");
        inOrder.verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");


    }

}