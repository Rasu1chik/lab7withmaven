package org.example.commands;

import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.utility.OutputColors;
import org.example.exeptions.IllegalArgumentsException;
import org.example.managers.CollectionManager;
import org.example.models.Flat;

import java.util.Collection;

/**
 * Команда 'show'
 *  Выводит в стандартный поток вывода все элементы коллекции в строковом представлении
 */
public class Show extends Command{
    private CollectionManager collectionManager;

    public Show(CollectionManager collectionManager) {
        super("show", ": вывести в стандартный поток вывода все элементы коллекции в строковом представлении");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        Collection<Flat> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            return new Response(ResponseStatus.ERROR, "Коллекция еще не иницализирована");
        }
        return new Response(ResponseStatus.OK,"Коллекция", collection);

    }
}
