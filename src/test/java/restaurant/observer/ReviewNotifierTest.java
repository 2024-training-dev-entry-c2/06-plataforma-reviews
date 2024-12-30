package restaurant.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewNotifierTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private ReviewNotifier notifier;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent));
        notifier = new ReviewNotifier();
    }

    @Test
    public void testUpdate() {
        String message = "Test notification";
        notifier.update(message);
        assertTrue(outContent.toString().contains("Notification del Observer: " + message));
    }
}