package org.example.commands;


import org.example.exeptions.*;
import org.example.managers.CollectionManager;
import org.example.models.Flat;
import org.example.models.Flat;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.exeptions.IllegalArgumentsException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * Команда 'filter_starts_with_name'
 * Выводит количество элементы начинающиеся с заданной подстроки
 */

public class FilterStartsWithName extends Command{
    private CollectionManager collectionManager;

    public FilterStartsWithName( CollectionManager collectionManager){
        super("filter_starts_with_name", "{name} : вывести элементы, значение поля пате которых начинается с заданной подстроки");
        this.collectionManager = collectionManager;
    }
    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        try {
            String subString = request.getArgs();
            List<Flat> filteredRoutes = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(route -> route.getName().startsWith(subString))
                    .collect(Collectors.toList());
            return new Response(ResponseStatus.OK,"Элементы с заданной подстроки : " + filteredRoutes);

        } catch (NumberFormatException exception) {
            return new Response(ResponseStatus.ERROR,"{name} должно быть числом типа String");
        }
    }

}

