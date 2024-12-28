package org.example.services.utils;

import java.time.LocalDate;

public interface IValidatorScanner {
    Integer integerScanner(String prompt);

    String stringScanner(String prompt);

    Float floatScanner(String prompt);

    LocalDate dateScanner(String prompt);

    void clearBuffer();
}
