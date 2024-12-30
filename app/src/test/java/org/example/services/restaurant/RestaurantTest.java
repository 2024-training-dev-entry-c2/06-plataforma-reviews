package org.example.services.restaurant;

import org.example.models.Restaurant;
import org.example.repositories.RestaurantRepository;
import org.example.services.utils.ValidatorScanner;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;


import static org.mockito.Mockito.*;


class RestaurantTest {
    private RestaurantRepository mockRepository;
    private ValidatorScanner mockValidatorScanner;
    private AddRestaurant addRestaurant;
    private RemoveRestaurant removeRestaurant;
    private AddReviewRestaurant addReviewRestaurant;
    private ShowReviewRestaurant showReviewRestaurant;
    @BeforeEach
    void  setup(){
        mockRepository = mock(RestaurantRepository.class);
        mockValidatorScanner = mock(ValidatorScanner.class);
        addRestaurant = new AddRestaurant(mockRepository, mockValidatorScanner);
        removeRestaurant = new RemoveRestaurant(mockRepository,mockValidatorScanner);
        addReviewRestaurant= new AddReviewRestaurant(mockRepository,mockValidatorScanner);
        showReviewRestaurant = new ShowReviewRestaurant(mockRepository,mockValidatorScanner);

    }
    @Test
    void testExecute() {
        String restaurantName = "Test Restaurant";
        String restaurantAddress = "123 Test St";
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn(restaurantName);
        when(mockValidatorScanner.stringScanner("Escribe el direccion del Restaurante"))
                .thenReturn(restaurantAddress);

        addRestaurant.execute();
        // Es una clase de Mockito que se utiliza para capturar argumentos pasados a un metodo mockeado.
        // En este caso, se usa para capturar el objeto Restaurant que se pasa al metodo addRestaurant del repositorio.
        ArgumentCaptor<Restaurant> captor = ArgumentCaptor.forClass(Restaurant.class);
        verify(mockRepository).addRestaurant(captor.capture());

        //Obtencion del Objeto Capturado
        Restaurant capturedRestaurant = captor.getValue();

        //verificacion del Objeto Capturado
        Assertions.assertNotNull(capturedRestaurant);
        Assertions.assertEquals(restaurantName,capturedRestaurant.getName());
//        Assertions.assertEquals(restaurantAddress,capturedRestaurant.getAddress());
        Assertions.assertNotNull(capturedRestaurant.getMenu());
        Assertions.assertEquals(restaurantName + " Menu", capturedRestaurant.getMenu().getName());

    }
    @Test
    void testRemoveRestaurants(){
        String restaurantName = "Test Restaurant";
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn(restaurantName);
        removeRestaurant.execute();

        verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        verify(mockRepository).removeRestaurant(restaurantName);
    }
    @Test
    void testAddReviewRestaurants(){
        String restaurantName = "Test Restaurant";
        String userName = "John Doe";
        String comment = "Great food!";
        float placeRating = 4.5f;
        float menuRating = 4.0f;
        float serviceRating = 5.0f;

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn(restaurantName);
        when(mockValidatorScanner.stringScanner("Ingrese su nombre"))
                .thenReturn(userName);
        when(mockValidatorScanner.stringScanner("Ingrese su comentario"))
                .thenReturn(comment);
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del lugar"))
                .thenReturn(placeRating);
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del menu"))
                .thenReturn(menuRating);
        when(mockValidatorScanner.floatScanner("ingrese de 0 a 5 la calificacion del del serviceRating"))
                .thenReturn(serviceRating);

        addReviewRestaurant.execute();
        // Verificaciones

        verify(mockValidatorScanner, times(3)).stringScanner(anyString());
        verify(mockValidatorScanner, times(3)).floatScanner(anyString());
    }
    @Test
    void testShowReviewRestaurant(){
        String restaurantName = "Restaurante Prueba";
        String address = "address Prueba";
        Restaurant restaurant = new Restaurant(restaurantName,address);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn(restaurantName);
        when(mockRepository.getRestaurant(restaurantName))
                .thenReturn(restaurant);


        showReviewRestaurant.execute();


        verify(mockRepository).getRestaurant(restaurantName);
        verify(mockRepository).showReview(restaurant);
        verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
    }

}