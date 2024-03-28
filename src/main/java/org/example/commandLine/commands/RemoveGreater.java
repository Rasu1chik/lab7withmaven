package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.commandLine.ConsoleColors;
import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArguments;
import org.example.managers.CollectionManager;
import org.example.models.Flat;
import org.example.models.forms.FlatForm;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_greater'
 * Удаляет из коллекции все элементы, превышающие заданный
 */
public class RemoveGreater extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public RemoveGreater(Console console, CollectionManager collectionManager) {
        super("remove_greater", " {element} : удалить из коллекции все элементы, превышающие заданный");
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
        class NoElements extends RuntimeException{

        }
        try {
            console.println(ConsoleColors.toColor("Создание объекта Flat", ConsoleColors.PURPLE));
            Flat newElement = new FlatForm(console).build();
            console.println(ConsoleColors.toColor("Создание объекта Flat окончено успешно!", ConsoleColors.PURPLE));
            Collection<Flat> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(flat -> flat.compareTo(newElement) >= 1)
                    .toList();
            collectionManager.removeElements(toRemove);
            console.println(ConsoleColors.toColor("Удалены элементы большие чем заданный", ConsoleColors.GREEN));
        } catch (NoElements e){
            console.printError("В коллекции нет элементов");
        } catch (ExceptionInFileMode e){
            console.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}
