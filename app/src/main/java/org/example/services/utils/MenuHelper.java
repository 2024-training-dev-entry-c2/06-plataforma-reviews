package org.example.services.utils;


import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class MenuHelper {

    private static IValidatorScanner validatorScanner;

    public MenuHelper(IValidatorScanner scanner) {
        validatorScanner = scanner;
    }

    private static void showOptions(String message, List<String> options) {
        System.out.println(message);
        AtomicInteger index = new AtomicInteger(1); // Iniciar el índice desde 1
        options.forEach(option -> System.out.println(index.getAndIncrement() + ". " + option));

    }

    public static Integer chooseOptions(String message, List<String> options) {
        System.out.println("________________________________________________________");
        showOptions(message, options);
        Integer input = getValue(options.size());
        System.out.println("________________________________________________________");
        return input-1;
    }

    static Integer getValue(Integer maxOption) {
        System.out.println("___________________________________________________________");
        Integer inputValue = validatorScanner.integerScanner("Ingrese un Numero :");
        return inputValue > maxOption ? 0 : inputValue;
    }


    public static String chooseOptionList(String message, List<String> options) {
        Integer index = chooseOptions(message, options);
        return (index == -1) ? null : options.get(index);
    }

}