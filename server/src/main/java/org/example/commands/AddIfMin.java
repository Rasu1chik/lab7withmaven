package org.example.commands;


import org.example.exeptions.IllegalArgumentsException;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.models.Flat;
import org.example.network.*;
import org.example.utility.OutputColors;

import java.util.Objects;

/**
 * Команда 'add_if_min'
 * Добавляет элемент в коллекцию если он меньше минимального
 */
public class AddIfMin extends Command {
    private CollectionManager collectionManager;

    public AddIfMin(CollectionManager collectionManager) {
        super("add_if_min", " {element}: добавить элемент в коллекцию если он меньше минимального");
        this.collectionManager = collectionManager;

    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException,InvalidForm {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        if (Objects.isNull(request.getObject())) {
            return new Response(ResponseStatus.OK);}
        if (request.getObject().compareTo(Objects.requireNonNull(collectionManager.getCollection().stream().filter(Objects::nonNull).min(Flat::compareTo).orElse(null))) <= -1){
            collectionManager.addElement(request.getObject());
            return new Response(ResponseStatus.OK,"Объект успешно добавлен!!!");
        }
        return new Response(ResponseStatus.ERROR,"Элемент меньше максимального");
    }

}