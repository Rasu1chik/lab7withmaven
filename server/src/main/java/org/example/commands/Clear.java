package org.example.commands;

import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.utility.OutputColors;
import org.example.exeptions.IllegalArgumentsException;
import org.example.managers.CollectionManager;

/**
 * Команда 'clear'
 * Очищает коллекцию
 */
public class Clear extends Command  {
    private CollectionManager collectionManager;


    public Clear(CollectionManager collectionManager) {
        super("clear", ": очистить коллекцию");
        this.collectionManager = collectionManager;

    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws org.example.exeptions.IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        collectionManager.clear();
        return new Response(ResponseStatus.OK,"Элементы удалены");
    }
}
