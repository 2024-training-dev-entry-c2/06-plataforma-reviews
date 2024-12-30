package org.example;

import org.example.utils.consoleUtils.ConsoleUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class AppTest {

    private ConsoleUtils console;

    @BeforeEach
    void setUp() {
        console = mock(ConsoleUtils.class);
    }

    @Test
    void testMain() {
        // Arrange: Set up expected behavior for mocks
        when(console.getString("Entra el nombre del restaurante: ")).thenReturn("La Pizzeria");
        when(console.getString("Entra el teléfono del restaurante: ")).thenReturn("123456789");
        when(console.getString("Entra la dirección del restaurante: ")).thenReturn("123 Main Street");
        when(console.getInteger("Entra la disponibilidad del restaurante: ")).thenReturn(20);

        // Act: Call the main method
        App.main(new String[]{});

        // Assert: Verify interactions and expected outcomes
        verify(console).getString("Entra el nombre del restaurante: ");
        verify(console).getString("Entra el teléfono del restaurante: ");
        verify(console).getString("Entra la dirección del restaurante: ");
        verify(console).getInteger("Entra la disponibilidad del restaurante: ");
    }
}