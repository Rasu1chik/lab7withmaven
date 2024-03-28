package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.exeptions.IllegalArguments;
import org.example.managers.CollectionManager;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Clear(Console console, CollectionManager collectionManager) {
        super("clear", ": очистить коллекцию");
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
        collectionManager.clear();
        console.println("Элементы удалены");
    }
}
