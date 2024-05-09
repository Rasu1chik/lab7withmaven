package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.commands.*;
import org.example.exeptions.ExitObliged;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.managers.FileManager;
import org.example.models.Flat;
import org.example.utility.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class App extends Thread {
    public static int PORT = 6087;
    private static final Printable console = new PrintConsole();
    private static final ConsoleOutput consoleOutput = new ConsoleOutput();

    static final Logger rootLogger = LogManager.getRootLogger();

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        try {
            return scanner.nextLine().trim();
        } catch (NoSuchElementException e) {
            return "";
        }
    }

    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();
        FileManager fileManager;

        if (args.length > 0 && args[0] != null && !args[0].isEmpty()) {
            // Первый аргумент командной строки используется в качестве пути к файлу
            fileManager = new FileManager(consoleOutput, collectionManager, args[0]);
        } else {
            consoleOutput.printError("Лее,друже, необходимо указать путь к файлу в качестве аргумента командной строки.");
            return;
        }

        // мб фиксить нужно
        try {
            App.rootLogger.info("Создание объектов");
            fileManager.isFindFile();
            fileManager.createObjects();
            App.rootLogger.info("Создание объектов успешно завершено");
        } catch (ExitObliged e) {
            console.println(OutputColors.toColor("До свидания!", OutputColors.YELLOW));
            App.rootLogger.error("Ошибка во времени создания объектов");
            return;
        } catch (InvalidForm e) {
            throw new RuntimeException(e);
        }

        CommandManager commandManager = new CommandManager(fileManager);
        commandManager.addCommand(List.of(
                new Help(commandManager),
                new Show(collectionManager),
                new AddElement(collectionManager),
                new AddIfMin(collectionManager),
                new Clear(collectionManager),
                new Execute(),
                new Exit(),
                new History(commandManager),
                new Info(collectionManager),
                new Update(collectionManager),
                new RemoveById(collectionManager),
                new RemoveGreater(collectionManager),
                new FilterByHouse(collectionManager),
                new FilterStartsWithName(collectionManager)




        ));
        App.rootLogger.debug("Создан объект менеджера команд");
        RequestHandler requestHandler = new RequestHandler(commandManager);
        App.rootLogger.debug("Создан объект обработчика запросов");
        Server server = new Server(PORT, requestHandler);
        App.rootLogger.debug("Создан объект сервера");

        new Thread(() -> {
            while (true) {
                String userInput = getUserInput();
                if (userInput.equalsIgnoreCase("save")) {
                    fileManager.saveObjects();
                }
            }
        }).start();

        Runtime.getRuntime().addShutdownHook(new Thread(new AutoSaveHook(fileManager)));


        server.run();


    }
}
