package org.example.commands;


import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArgumentsException;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.utility.OutputColors;
import org.example.network.ResponseStatus;
import org.example.network.Response;

import java.util.Objects;

/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddElement extends Command {
    private CollectionManager collectionManager;


    public AddElement(CollectionManager collectionManager) {
        super("add", " {element}: добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргумaddенты команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException,InvalidForm {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();

        collectionManager.addElement(request.getObject());
        return new Response(ResponseStatus.OK, "Объект успешно добавлен");

    }
}