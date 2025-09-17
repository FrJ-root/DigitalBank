package com.digitalbank.utils;

public class ConsoleUtils {
    public static void clearScreen() {
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J"); // ANSI escape for Linux/Mac
                System.out.flush();
            }
        } catch (Exception e) {
            // If everything fails, just print new lines
            for (int i = 0; i < 50; i++) {
                System.out.println();
            }
        }
    }
}
