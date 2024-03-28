package org.example.models.forms;

import org.example.commandLine.*;
import org.example.models.Coordinates;
import org.example.exeptions.ExceptionInFileMode;
/**
 * Форма для ввода координат.
 */
public class CoordinatesForm extends Form<Coordinates> {
    private final Printable console;
    private final UserInput scanner;

    public CoordinatesForm(Printable console) {
        this.console = (Console.isFileMode()) ? new BlankConsole() : console;
        this.scanner = (Console.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }

    /**
     * Строит новый объект класса {@link Coordinates} на основе введенных координат.
     *
     * @return объект класса {@link Coordinates}.
     */
    @Override
    public Coordinates build() {
        return new Coordinates(askX(), askY());
    }

    /**
     * Запрашивает ввод координаты X у пользователя.
     *
     * @return введенное значение координаты X.
     */
    private double askX() {
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату X", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("Координата X должна быть числом типа double");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }

    /**
     * Запрашивает ввод координаты Y у пользователя.
     *
     * @return введенное значение координаты Y.
     */
    private int askY() {
        while (true) {
            console.println(ConsoleColors.toColor("Введите координату Y", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("Координата Y должна быть целым числом типа int");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
