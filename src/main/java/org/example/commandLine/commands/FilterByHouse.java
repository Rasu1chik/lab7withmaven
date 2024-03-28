package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.exeptions.IllegalArguments;
import org.example.managers.CollectionManager;
import org.example.models.Flat;

import java.util.Collection;


public class FilterByHouse extends Command {
    private CollectionManager collectionManager;
    private Console console;

    public FilterByHouse(Console console, CollectionManager collectionManager) {
        super("filter_by_house"," house, вывести элементы, значение поля house которых равно заданному");
        this.collectionManager = collectionManager;
        this.console = console;
    }

    @Override
    public void execute(String args) throws IllegalArguments {
        if (args.isBlank()) throw new IllegalArguments();
        String[] argArray = args.trim().split(",");
        if (argArray.length != 4) {
            console.printError("Неправильное количество аргументов.Вводи значения полей через запятую!");
            return;
        }
        String name = argArray[0].trim();
        long year = Long.parseLong(argArray[1].trim());
        Long numberOfFloors = Long.parseLong(argArray[2].trim());
        Integer numberOfFlatsOnFloor = Integer.parseInt(argArray[3].trim());

        // Вызываем метод фильтрации коллекции по значениям полей house
        Collection<Flat> filteredFlats = collectionManager.filterByHouse(name, year, numberOfFloors, numberOfFlatsOnFloor);

        if (filteredFlats.isEmpty()) {
            console.println("Элементы с указанными значениями полей house не найдены");
        } else {
            console.println("Элементы с указанными значениями полей house:");
            for (Flat flat : filteredFlats) {
                console.println(flat.toString());
            }
        }
    }
}