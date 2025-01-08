package org.nahulem.utils;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.nahulem.controllers.interfaces.ICommandController;
import org.nahulem.controllers.menu.AddDishController;
import org.nahulem.controllers.menu.DeleteDishController;
import org.nahulem.controllers.menu.UpdateDishController;
import org.nahulem.controllers.restaurant.CreateRestaurantController;
import org.nahulem.controllers.restaurant.DeleteRestaurantController;
import org.nahulem.controllers.restaurant.ListRestaurantController;
import org.nahulem.controllers.restaurant.UpdateRestaurantController;
import org.nahulem.controllers.review.CreateDishReviewController;
import org.nahulem.controllers.review.CreateRestaurantReviewController;
import org.nahulem.controllers.review.ShowDishReviewController;
import org.nahulem.controllers.review.ShowRestaurantReviewController;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

class MainMenuTest {
    private Validator mockValidator;
    private CreateRestaurantController mockCreateRestaurantController;
    private UpdateRestaurantController mockUpdateRestaurantController;
    private DeleteRestaurantController mockDeleteRestaurantController;
    private ListRestaurantController mockListRestaurantController;
    private AddDishController mockAddDishController;
    private UpdateDishController mockUpdateDishController;
    private DeleteDishController mockDeleteDishController;
    private CreateDishReviewController mockCreateDishReviewController;
    private ShowDishReviewController mockShowDishReviewController;
    private CreateRestaurantReviewController mockCreateRestaurantReviewController;
    private ShowRestaurantReviewController mockShowRestaurantReviewController;
    private MainMenu mainMenu;

    private Map<Integer, ICommandController> controllerMap;

    @BeforeEach
    void setUp() {
        mockValidator = mock(Validator.class);
        mockCreateRestaurantController = mock(CreateRestaurantController.class);
        mockUpdateRestaurantController = mock(UpdateRestaurantController.class);
        mockDeleteRestaurantController = mock(DeleteRestaurantController.class);
        mockListRestaurantController = mock(ListRestaurantController.class);
        mockAddDishController = mock(AddDishController.class);
        mockUpdateDishController = mock(UpdateDishController.class);
        mockDeleteDishController = mock(DeleteDishController.class);
        mockCreateDishReviewController = mock(CreateDishReviewController.class);
        mockShowDishReviewController = mock(ShowDishReviewController.class);
        mockCreateRestaurantReviewController = mock(CreateRestaurantReviewController.class);
        mockShowRestaurantReviewController = mock(ShowRestaurantReviewController.class);
        mainMenu = new MainMenu(mockAddDishController, mockValidator, mockCreateRestaurantController, mockUpdateRestaurantController, mockDeleteRestaurantController, mockListRestaurantController, mockUpdateDishController, mockDeleteDishController, mockCreateDishReviewController, mockShowDishReviewController, mockCreateRestaurantReviewController, mockShowRestaurantReviewController);

        controllerMap = new HashMap<>();
        controllerMap.put(1, mockCreateRestaurantController);
        controllerMap.put(2, mockListRestaurantController);
        controllerMap.put(3, mockUpdateRestaurantController);
        controllerMap.put(4, mockDeleteRestaurantController);
        controllerMap.put(5, mockAddDishController);
        controllerMap.put(6, mockUpdateDishController);
        controllerMap.put(7, mockDeleteDishController);
        controllerMap.put(8, mockCreateDishReviewController);
        controllerMap.put(9, mockShowDishReviewController);
        controllerMap.put(10, mockCreateRestaurantReviewController);
        controllerMap.put(11, mockShowRestaurantReviewController);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
    void testValidOption(int option) {
        when(mockValidator.readInt(anyString())).thenReturn(option, 12);
        mainMenu.execute();

        verify(mockValidator, times(2)).readInt(anyString());
        verify(controllerMap.get(option)).execute();
    }

    @Test
    void executeInvalidOption() {
        when(mockValidator.readInt(anyString())).thenReturn(99, 11, 12);

        mainMenu.execute();

        verify(mockValidator, times(3)).readInt(anyString());

        verify(mockShowRestaurantReviewController, times(1)).execute();

        verifyNoInteractions(mockCreateRestaurantController);
        verifyNoInteractions(mockUpdateRestaurantController);
    }
}
