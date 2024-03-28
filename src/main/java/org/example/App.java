package org.example;

import org.example.commandLine.ConsoleColors;
import org.example.exeptions.ExitObliged;
import org.example.exeptions.InvalidForm;
import org.example.managers.*;
import org.example.commandLine.Console;
import org.example.commandLine.commands.*;


import java.util.List;



public class App {

    public static void main(String[] args) {
        Console console = new Console();
        CollectionManager collectionManager = new CollectionManager();
        CommandManager commandManager = new CommandManager();



        //String s = args[0];

        String s = "/Users/rasulabakarov/Downloads/lab5withmaven/file.csv";
        FileManager fileManager = new FileManager(console, collectionManager, s);
        try {
            if (fileManager.isFindFile()) {
                fileManager.createObjects();
            }


        } catch (ExitObliged e){
            console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
            return;
        } catch (InvalidForm e) {
            throw new RuntimeException(e);
        }

        commandManager.addCommand(List.of(
                new Help(console, commandManager),
                new Info(console, collectionManager),
                new Show(console, collectionManager),
                new AddElement(console, collectionManager),
                new Update(console, collectionManager),
                new RemoveById(console, collectionManager),
                new Clear(console, collectionManager),
                new Save(console, fileManager),
                new AddIfMin(console,collectionManager),
                new Exit(),
                new Execute(console, fileManager,commandManager),
                new RemoveGreater(console, collectionManager),
                new History(console, commandManager),
                new FilterByHouse(console,collectionManager),
                new FilterStartsWithName(console,collectionManager),
                new PrintFieldDescendingFlat(console,collectionManager)

        ));
        new RuntimeManager(console, commandManager).interactiveMode();
    }
}
