package org.example.commandLine;


import org.example.utility.ScannerManager;

import java.util.Scanner;

/**
 * Класс для стандартного ввода через консоль
 */
public class ConsoleInput implements UserInput {
    private static final Scanner userScanner = ScannerManager.getUserScanner();


    public String nextLine() {
        return userScanner.nextLine();
    }
}
