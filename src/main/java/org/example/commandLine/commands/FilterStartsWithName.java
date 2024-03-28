package org.example.commandLine.commands;
import org.example.commandLine.Console;
import org.example.managers.CollectionManager;
import org.example.exeptions.*;
import org.example.models.Flat;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Objects;

public class FilterStartsWithName extends Command{
    private Console console;
    private CollectionManager collectionManager;


    public FilterStartsWithName(Console console, CollectionManager collectionManager){
        super("filter_starts_with_name", "{name} : вывести элементы, значение поля пате которых начинается с заданной подстроки");
        this.console = console;
        this.collectionManager = collectionManager;
    }
    /**
     * Исполнить команду
     * @param args аргументы команды
     * @throws IllegalArguments неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArguments, ExitObliged, CommandRuntimeError {
        if(args.isBlank()) throw new IllegalArguments();
        String subString = args.trim();
        List<Flat> filteredFlats = collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .filter(route -> route.getName().startsWith(subString))
                .collect(Collectors.toList());

        if (filteredFlats.isEmpty()) {
            console.printError("Нет элементов начинающихся с такой подстроки");
        } else {
            filteredFlats.forEach(route -> console.println(route.toString()));
        }

    }

}