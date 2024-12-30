package org.example.reviews.utils.interfaces;

import java.time.LocalDate;

public interface IConsole {
    String readLine(String message);
    void writeLine(String message);
    Integer readInt(String message);
    LocalDate readDate(String message);
    Double readDouble(String message);
    Boolean readBooleanYesOrNo(String message);
    Float readFloat(String message);
}
