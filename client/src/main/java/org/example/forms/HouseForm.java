package org.example.forms;

import org.example.models.House;
import org.example.commandLine.*;
import org.example.utility.OutputColors;


public class HouseForm extends Form<House> {
    private final Printable console;
    private final ConsoleInput scanner;

    public HouseForm(Printable console,ConsoleInput scanner) {
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
        console.println(OutputColors.toColor("Введите название дома:", OutputColors.GREEN));
        return scanner.nextLine().trim();
    }

    private long askYear() {
        while (true) {
            console.println(OutputColors.toColor("Введите год постройки дома:", OutputColors.GREEN));
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                console.printError("Пожалуйста, введите корректное число для года постройки.");
            }
        }
    }

    private Long askNumberOfFloors() {
        while (true) {
            console.println(OutputColors.toColor("Введите количество этажей в доме:", OutputColors.GREEN));
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                console.printError("Пожалуйста, введите количество этажей в доме.");
            } else {
                try {
                    return Long.parseLong(input);
                } catch (NumberFormatException e) {
                    console.printError("Пожалуйста, введите корректное число для количества этажей в доме.");
                }
            }
        }
    }


    private Integer askNumberOfFlatsOnFloor() {
        while (true) {
            console.println(OutputColors.toColor("Введите количество квартир на одном этаже:", OutputColors.GREEN));
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                console.printError("Пожалуйста, введите количество квартир на этаже.");
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
