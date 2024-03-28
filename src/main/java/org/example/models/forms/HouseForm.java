package org.example.models.forms;

import org.example.models.House;
import org.example.commandLine.*;


public class HouseForm extends Form<House> {
    private final Printable console;
    private final UserInput scanner;

    public HouseForm(Printable console, UserInput scanner) {
        this.console = console;
        this.scanner = scanner;
    }

    @Override
    public House build() {
        String name = askName();
        long year = askYear();
        Long numberOfFloors = askNumberOfFloors();
        Integer numberOfFlatsOnFloor = askNumberOfFlatsOnFloor();

        return new House(name, year, numberOfFloors, numberOfFlatsOnFloor);
    }

    private String askName() {
        console.println(ConsoleColors.toColor("Введите название дома:", ConsoleColors.GREEN));
        return scanner.nextLine().trim();
    }

    private long askYear() {
        while (true) {
            console.println(ConsoleColors.toColor("Введите год постройки дома:", ConsoleColors.GREEN));
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                console.printError("Пожалуйста, введите корректное число для года постройки.");
            }
        }
    }

    private Long askNumberOfFloors() {
        console.println(ConsoleColors.toColor("Введите количество этажей в доме:", ConsoleColors.GREEN));
        String input = scanner.nextLine().trim();
        return input.isEmpty() ? null : Long.parseLong(input);
    }

    private Integer askNumberOfFlatsOnFloor() {
        while (true) {
            console.println(ConsoleColors.toColor("Введите количество квартир на одном этаже:", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                return null;
            } else {
                try {
                    int numberOfFlats = Integer.parseInt(input);
                    if (numberOfFlats <= 0) {
                        console.printError("Количество квартир на этаже должно быть положительным числом.");
                    } else {
                        return numberOfFlats;
                    }
                } catch (NumberFormatException e) {
                    console.printError("Пожалуйста, введите корректное число для количества квартир на этаже.");
                }
            }
        }
    }
}
