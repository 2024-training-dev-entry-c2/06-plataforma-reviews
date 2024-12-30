package org.example;

import org.example.repositories.ReviewNotifier;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReviewNotifierTest {

  @Test
  void testUpdate() {
    ReviewNotifier reviewNotifier = new ReviewNotifier();

    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(outputStream);
    System.setOut(printStream);

    String message = "Nueva reseña añadida";

    reviewNotifier.update(message);

    assertTrue(outputStream.toString().contains(message));

    System.setOut(System.out);
  }
}

