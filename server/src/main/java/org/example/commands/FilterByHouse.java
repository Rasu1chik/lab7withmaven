package org.example.commands;

import org.example.exeptions.IllegalArgumentsException;
import org.example.managers.CollectionManager;
import org.example.models.Flat;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;

import java.util.Collection;

public class FilterByHouse extends Command {
    private CollectionManager collectionManager;

    public FilterByHouse(CollectionManager collectionManager) {
        super("filter_by_house", " house, вывести элементы, значение поля house которых равно заданному");
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException();
        String[] argArray = request.getArgs().trim().split(",");
        if (argArray.length != 4) {
            return new Response(ResponseStatus.ERROR, "Неправильное количество аргументов. Вводи значения полей через запятую!");
        }

        String name = argArray[0].trim();
        long year = Long.parseLong(argArray[1].trim());
        Long numberOfFloors = Long.parseLong(argArray[2].trim());
        Integer numberOfFlatsOnFloor = Integer.parseInt(argArray[3].trim());

        // Вызываем метод фильтрации коллекции по значениям полей house
        Collection<Flat> filteredFlats = collectionManager.filterByHouse(name, year, numberOfFloors, numberOfFlatsOnFloor);

        if (filteredFlats.isEmpty()) {
            return new Response(ResponseStatus.ERROR, "Элементы с указанными значениями полей house не найдены");
        } else {
            StringBuilder responseBuilder = new StringBuilder();
            responseBuilder.append("Элементы с указанными значениями полей house:\n");
            for (Flat flat : filteredFlats) {
                responseBuilder.append(flat.toString()).append("\n");
            }
            return new Response(ResponseStatus.OK,responseBuilder.toString());
        }
    }
}
