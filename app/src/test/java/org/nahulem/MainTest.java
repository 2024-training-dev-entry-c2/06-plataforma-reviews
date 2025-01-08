package org.nahulem;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class MainTest {

    @Test
    void mainWithNullArgsThrowsException() {
        assertThrows(NullPointerException.class, () -> Main.main(null));
    }

    @Test
    void mainWithEmptyArgsExecutesSuccessfully() {
        String simulatedInput = "12\n";
        InputStream originalIn = System.in;
        System.setIn(new ByteArrayInputStream(simulatedInput.getBytes()));

        try {
            assertDoesNotThrow(() -> Main.main(new String[]{}));
        } finally {
            System.setIn(originalIn);
        }
    }
}
