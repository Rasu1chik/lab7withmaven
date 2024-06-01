package org.example;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.commands.*;
import org.example.managers.CollectionManager;
import org.example.managers.CommandManager;
import org.example.utility.DatabaseHandler;
import org.example.utility.Server;

import java.util.List;

public class App extends Thread {


    public static int PORT = 6085;
    public static final Logger rootLogger = LogManager.getLogger(App.class);


    public static void main(String[] args) {
        CollectionManager collectionManager = new CollectionManager();

        rootLogger.info("¯\\_(ツ)_/¯¯\\_(ツ)_/¯(◕‿◕)ЗАПУСК СЕРВЕРА В КОСМОС(◕‿◕)¯\\_(ツ)_/¯¯\\_(ツ)_/¯");


        CommandManager commandManager = new CommandManager(DatabaseHandler.getDatabaseManager());
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
                new FilterStartsWithName(collectionManager),
                new FilterByHouse(collectionManager),
                new RemoveGreater(collectionManager),
                new Ping(),
                new Register(DatabaseHandler.getDatabaseManager())

        ));
        App.rootLogger.debug("Создан объект менеджера команд");

        Server server = new Server(commandManager, DatabaseHandler.getDatabaseManager());
        App.rootLogger.debug("Создан объект сервера");

        server.run();

    }
}
