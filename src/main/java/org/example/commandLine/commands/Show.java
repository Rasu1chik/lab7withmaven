package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.exeptions.IllegalArguments;
import org.example.managers.CollectionManager;
import org.example.models.Flat;

import java.util.Collection;

/**
 * Команда 'show'
 *  Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Show(Console console, CollectionManager collectionManager) {
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        Collection<Flat> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            console.printError("Коллекция еще не инициализирована");
            return;
        }
        console.println(collection.toString());
    }
}
