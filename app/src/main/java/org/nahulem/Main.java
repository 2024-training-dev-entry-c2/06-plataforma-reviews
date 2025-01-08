package org.nahulem;

import org.nahulem.config.AppConfig;
import org.nahulem.utils.MainMenu;

public class Main {
    public static void main(String[] args) {
        if (args == null) {
            throw new NullPointerException("Args cannot be null");
        }

        AppConfig config = new AppConfig();
        MainMenu mainMenu = config.createMainMenu();

        mainMenu.execute();
    }
}
