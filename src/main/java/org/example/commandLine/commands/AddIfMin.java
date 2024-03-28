package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.commandLine.ConsoleColors;
import org.example.exeptions.IllegalArguments;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.models.Flat;
import org.example.models.forms.FlatForm;

import java.util.Objects;

/**
 * Команда 'add_if_min'
 * Добавляет элемент в коллекцию если он меньше минимального
 */
public class AddIfMin extends Command {
    private CollectionManager collectionManager;
    private Console console;

    public AddIfMin(Console console, CollectionManager collectionManager) {
        super("add_if_min", " {element}: добавить элемент в коллекцию если он меньше минимального");
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
        try {
            console.println(ConsoleColors.toColor("Создание объекта StudyGroup", ConsoleColors.PURPLE));
            Flat newElement = new FlatForm(console).build();
            console.println(ConsoleColors.toColor("Создание объекта StudyGroup окончено успешно!", ConsoleColors.PURPLE));
            if (newElement.compareTo(collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .min(Flat::compareTo)
                    .orElse(null)) <= 0) {
                collectionManager.addElement(newElement);
                console.println(ConsoleColors.toColor("Объект успешно добавлен", ConsoleColors.GREEN));
            } else {
                console.println(ConsoleColors.toColor("Элемент больше минимального", ConsoleColors.RED));
            }
        } catch (InvalidForm invalidForm) {
            console.printError("Поля объекта не валидны! Объект не создан!");
        }
    }
}
