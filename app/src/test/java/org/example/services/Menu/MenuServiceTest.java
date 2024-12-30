package org.example.services.Menu;

import org.example.models.DishFood;
import org.example.models.Restaurant;
import org.example.repositories.MenuRepository;
import org.example.repositories.RestaurantRepository;

import org.example.services.utils.IValidatorScanner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class MenuServiceTest {
    private RestaurantRepository mockRepository;
    private MenuRepository mockMenuRepository;
    private IValidatorScanner mockValidatorScanner;
    private AddDishFood addDishFood;
    private RemoveDishFood removeDishFood;
    private ShowDishFood showDishFood;
    @BeforeEach
    void  setup(){
        mockRepository = mock(RestaurantRepository.class);
        mockValidatorScanner = mock(IValidatorScanner.class);
        addDishFood = new AddDishFood(mockRepository,mockMenuRepository,mockValidatorScanner);



    }
    @Test
    void AddDishFoodTest() {
        String restaurantName = "Restaurante Prueba";
        String address = "address Prueba";
        String dishName = "Plato Prueba";
        String description = "Descripción del plato";
        Double price = 15.99;

        Restaurant restaurant = new Restaurant(restaurantName,address);

        when(mockValidatorScanner.stringScanner("Escribe el nombre del Restaurante"))
                .thenReturn(restaurantName);
        when(mockRepository.getRestaurant(restaurantName))
                .thenReturn(restaurant);
        when(mockValidatorScanner.stringScanner("Escribe el nombre del plato:"))
                .thenReturn(dishName);
        when(mockValidatorScanner.stringScanner("Escribe una descripcion:"))
                .thenReturn(description);
        when(mockValidatorScanner.doubleScanner("Escribe el precio:"))
                .thenReturn(price);

        addDishFood.execute();
        verify(mockValidatorScanner).stringScanner("Escribe el nombre del Restaurante");
        verify(mockRepository).getRestaurant(restaurantName);
        verify(mockValidatorScanner).stringScanner("Escribe el nombre del plato:");
        verify(mockValidatorScanner).stringScanner("Escribe una descripcion:");
        verify(mockValidatorScanner).doubleScanner("Escribe el precio:");


    }
}