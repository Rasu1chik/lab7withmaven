package org.example.models.forms;

import org.example.commandLine.*;
import org.example.models.Coordinates;
import org.example.models.Flat;
import org.example.models.*;
import org.example.exeptions.ExceptionInFileMode;
import java.util.TreeSet;
import java.util.Objects;

public class FlatForm extends Form<Flat>{
    private final Printable console;
    private final UserInput scanner;

    public FlatForm(Printable console) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.scanner = (Console.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    public Flat build(){
        return new Flat(
                askName(),
                askCoordinates(),
                askArea(),
                askNumberOfRooms(),
                askFurnish(),
                askView(),
                askTransport(),
                askHouse()
        );
    }

    private String askName(){
        String name;
        while (true){
            console.println(ConsoleColors.toColor("Введите название квартиры", ConsoleColors.GREEN));
            name = scanner.nextLine().trim();
            if (name.isEmpty()){
                console.printError("Название квартиры не может быть пустым");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
            else{
                return name;
            }
        }
    }

    private Coordinates askCoordinates(){
        return new CoordinatesForm(console).build();
    }

    private double askArea(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите площадь квартиры", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("Площадь квартиры должна быть числом");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }

    private Long askNumberOfRooms(){
        while (true) {
            console.println(ConsoleColors.toColor("Введите количество комнат", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Long.parseLong(input);
            } catch (NumberFormatException exception) {
                console.printError("Количество комнат должно быть числом типа long");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }

    private Furnish askFurnish(){
        return new FurnishForm(console).build();
    }

    private View askView(){
        return new ViewForm(console,"тип").build();
    }

    private Transport askTransport(){
        return new TransportForm(console).build();
    }

    private House askHouse(){
        return new HouseForm(console,scanner).build();
    }
}
