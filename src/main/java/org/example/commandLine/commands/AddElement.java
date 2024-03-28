package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.commandLine.ConsoleColors;
import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArguments;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.models.forms.FlatForm;

/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddElement extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public AddElement(Console console, CollectionManager collectionManager) {
        super("add", " {element}: добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргумaddенты команды
     */
    @Override
    public void execute(String args) throws IllegalArguments {
        if (!args.isBlank()) throw new IllegalArguments();
        try {
            console.println(ConsoleColors.toColor("Создание объекта Flat", ConsoleColors.PURPLE));
            collectionManager.addElement(new FlatForm(console).build());
            console.println(ConsoleColors.toColor("Создание объекта Flat окончено успешно!", ConsoleColors.PURPLE));
        } catch (InvalidForm invalidForm) {
            console.printError("Поля объекта не валидны! Объект не создан!");
        } catch (ExceptionInFileMode e){
            console.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}
