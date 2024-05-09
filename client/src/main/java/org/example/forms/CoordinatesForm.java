package org.example.forms;

import org.example.commandLine.*;
import org.example.models.Coordinates;
import org.example.exeptions.ExceptionInFileMode;
import org.example.utility.ExecuteFileManager;
import org.example.utility.OutputColors;
/**
 * Форма для ввода координат.
 */
public class CoordinatesForm extends Form<Coordinates> {
    private final Printable console;
    private final ConsoleInput scanner;

    public CoordinatesForm(Printable console) {
        this.console = (ConsoleOutput.isFileMode()) ? new PrintConsole() : console;
        this.scanner = (ConsoleOutput.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
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
            console.println(OutputColors.toColor("Введите координату X", OutputColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException exception) {
                console.printError("Координата X должна быть числом типа double");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
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
            console.println(OutputColors.toColor("Введите координату Y", OutputColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("Координата Y должна быть целым числом типа int");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
