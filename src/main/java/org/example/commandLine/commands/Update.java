package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.commandLine.ConsoleColors;
import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArguments;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.models.Flat;
import org.example.models.forms.FlatForm;

/**
 * Команда 'update'
 * Обновляет значение элемента коллекции, id которого равен заданному
 */
public class Update extends Command{
    private CollectionManager collectionManager;
    private Console console;

    public Update(Console console, CollectionManager collectionManager) {
        super("update", " id {element}: обновить значение элемента коллекции, id которого равен заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArguments{
        if (args.isBlank()) throw new IllegalArguments();
        class NoSuchId extends RuntimeException{

        }
        try {
            int id = Integer.parseInt(args.trim());
            if (!collectionManager.checkExist(id)) throw new NoSuchId();
            console.println(ConsoleColors.toColor("Создание нового объекта Flat", ConsoleColors.PURPLE));
            Flat newFlat = new FlatForm(console).build();
            collectionManager.editById(id, newFlat);
            console.println(ConsoleColors.toColor("Создание нового объекта Flat окончено успешно!", ConsoleColors.PURPLE));
        } catch (NoSuchId err) {
            console.printError("В коллекции нет элемента с таким id");
        } catch (InvalidForm invalidForm) {
            console.printError("Поля объекта не валидны! Объект не создан!");
        } catch (NumberFormatException exception) {
            console.printError("id должно быть числом типа int");
        } catch (ExceptionInFileMode e){
            console.printError("Поля в файле не валидны! Объект не создан");
        }
    }
}
