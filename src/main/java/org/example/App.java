package org.example;

import org.example.commandLine.ConsoleColors;
import org.example.exeptions.*;
import org.example.managers.*;
import org.example.commandLine.Console;
import org.example.commandLine.commands.*;
import org.exa


import java.util.List;



public class App {
    private static CommandManager commandManager; // Объявляем commandManager как поле класса

    public App(Console console, CollectionManager collectionManager, FileManager fileManager) {
    }

    public static void main(String[] args) {
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        System.out.println(Flat.);


        /*
        String filePath = System.getenv("FILE_PATH");

        if (filePath == null) {
            System.out.println("Переменная среды FILE_PATH не была установлена.");
            return;
        }

         */


        String filePath = "/Users/rasulabakarov/Downloads/lab5withmaven/file.csv";
        FileManager fileManager = new FileManager(console, collectionManager, filePath);
        try {
            if (fileManager.isFindFile()) {
                fileManager.createObjects();
            }
        } catch (ExitObliged e) {
            console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
            return;
        } catch (InvalidForm e) {
            console.printError("Ошибка при чтении CSV: " + e.getMessage());
            // Обработка InvalidForm
        } catch (RuntimeException e) {
            console.printError("Произошла ошибка: " + e.getMessage());
        }

        commandManager = new CommandManager(console, null, collectionManager, fileManager); // Сначала передаем null
        commandManager.addCommand(new Help(console, commandManager));
        commandManager.addCommand(new History(console, commandManager));
        commandManager.addCommand(new Execute(console, fileManager, commandManager));
        // Добавляем команду Help после инициализации commandManager
        new App(console, collectionManager, fileManager);
        new RuntimeManager(console, commandManager).interactiveMode();
    }

}
