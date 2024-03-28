package org.example.commandLine.commands;

import org.example.commandLine.Console;
import org.example.managers.CollectionManager;
import org.example.models.Flat;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Команда для вывода значений поля house всех элементов в порядке убывания
 */
public class PrintFieldDescendingFlat extends Command {
    private final Console console;
    private final CollectionManager collectionManager;

    public PrintFieldDescendingFlat(Console console, CollectionManager collectionManager) {
        super("print_field_descending_flat", "вывести значения поля house всех элементов в порядке убывания");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     * @param args аргументы команды (в данном случае они не используются)
     */
    @Override
    public void execute(String args) {
        // Получаем список квартир, сортируем по полю house в обратном порядке и выводим информацию о каждой квартире
        List<Flat> sortedFlats = collectionManager.getCollection().stream()
                .sorted(Comparator.comparing((Flat flat) -> flat.getHouse().getName()).reversed())
                .collect(Collectors.toList());

        for (Flat flat : sortedFlats) {
            console.println("House: " + flat.getHouse().getName() + ", Year: " + flat.getHouse().getYear() +
                    ", Number of floors: " + flat.getHouse().getNumberOfFloors() + ", Number of flats on floor: " +
                    flat.getHouse().getNumberOfFlatsOnFloor());
        }
    }
}
