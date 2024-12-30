package org.example.utils;

import org.example.controllers.interfaces.ICommandController;
import org.example.controllers.menu.AddDishController;
import org.example.controllers.menu.DeleteDishController;
import org.example.controllers.menu.UpdateDishController;
import org.example.controllers.restaurant.CreateRestaurantController;
import org.example.controllers.restaurant.DeleteRestaurantController;
import org.example.controllers.restaurant.ListRestaurantController;
import org.example.controllers.restaurant.UpdateRestaurantController;
import org.example.controllers.review.CreateDishReviewController;
import org.example.controllers.review.CreateRestaurantReviewController;
import org.example.controllers.review.ListDishReviewController;
import org.example.controllers.review.ListRestaurantReviewController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.HashMap;
import java.util.Map;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
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
	private ListDishReviewController mockListDishReviewController;
	private CreateRestaurantReviewController mockCreateRestaurantReviewController;
	private ListRestaurantReviewController mockListRestaurantReviewController;
	private MainMenu menu;

	private Map<Integer, ICommandController> controllers;

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
		mockListDishReviewController = mock(ListDishReviewController.class);
		mockCreateRestaurantReviewController = mock(CreateRestaurantReviewController.class);
		mockListRestaurantReviewController = mock(ListRestaurantReviewController.class);
		menu = new MainMenu(mockValidator, mockCreateRestaurantController, mockUpdateRestaurantController, mockDeleteRestaurantController, mockListRestaurantController, mockAddDishController, mockUpdateDishController, mockDeleteDishController, mockCreateDishReviewController, mockListDishReviewController, mockCreateRestaurantReviewController, mockListRestaurantReviewController);

		controllers = new HashMap<>();
		controllers.put(1, mockCreateRestaurantController);
		controllers.put(2, mockUpdateRestaurantController);
		controllers.put(3, mockDeleteRestaurantController);
		controllers.put(4, mockListRestaurantController);
		controllers.put(5, mockAddDishController);
		controllers.put(6, mockUpdateDishController);
		controllers.put(7, mockDeleteDishController);
		controllers.put(8, mockCreateDishReviewController);
		controllers.put(9, mockListDishReviewController);
		controllers.put(10, mockCreateRestaurantReviewController);
		controllers.put(11, mockListRestaurantReviewController);
	}

	@ParameterizedTest
	@ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11})
	void execute(int option) {
		when(mockValidator.readInteger(anyString())).thenReturn(option, 12);
		menu.execute();

		verify(mockValidator, times(2)).readInteger(anyString());
		verify(controllers.get(option)).execute();
	}

	@Test
	void executeWithInvalidOption() {
		when(mockValidator.readInteger(anyString())).thenReturn(14, 11, 12);
		menu.execute();

		verify(mockValidator, times(3)).readInteger(anyString());
		verify(controllers.get(11)).execute();
	}
}