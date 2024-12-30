package org.example;

import org.example.utils.AppMenu;
import org.example.Interface.IConsoleHandler;
import org.example.utils.HandleOption;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class MainTest {

    @Mock
    private IConsoleHandler mockConsoleHandler;
    @Mock
    private AppMenu mockAppMenu;
    @Mock
    private HandleOption mockHandleOption;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Test Main Method")
    void testMainMethod() {
        when(mockConsoleHandler.readLine()).thenReturn("1", "0");

        Main mainApp = new Main(mockAppMenu, mockHandleOption, mockConsoleHandler);

        Thread mainThread = new Thread(() -> {
            Main.keepRunning = true;
            mainApp.run();
        });
        mainThread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main.keepRunning = false;

        verify(mockAppMenu, atLeastOnce()).displayMenu();
        verify(mockConsoleHandler, atLeastOnce()).readLine();
        verify(mockHandleOption, atLeastOnce()).execute(anyInt());
    }

    @Test
    @DisplayName("Test Constructor")
    void testConstructor() {
        Main mainApp = new Main(mockAppMenu, mockHandleOption, mockConsoleHandler);
        assertNotNull(mainApp);
    }

    @Test
    @DisplayName("Test Run Method")
    void testRunMethod() {
        when(mockConsoleHandler.readLine()).thenReturn("0");

        Main mainApp = new Main(mockAppMenu, mockHandleOption, mockConsoleHandler);

        Thread mainThread = new Thread(() -> {
            Main.keepRunning = true;
            mainApp.run();
        });
        mainThread.start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Main.keepRunning = false;

        verify(mockAppMenu, atLeastOnce()).displayMenu();
        verify(mockConsoleHandler, atLeastOnce()).readLine();
        verify(mockHandleOption, atLeastOnce()).execute(anyInt());
    }
}