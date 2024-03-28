package org.example.models.forms;

import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.models.Furnish;

/**
 * Форма для выбора типа отделки
 */
public class FurnishForm extends Form<Furnish> {
    private final Printable console;
    private final UserInput scanner;

    public FurnishForm(Printable console) {
        this.console = (Console.isFileMode()) ? new BlankConsole() : console;
        this.scanner = (Console.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }

    /**
     * Строит новый элемент перечисления {@link Furnish}
     *
     * @return объект перечисления {@link Furnish}
     */
    @Override
    public Furnish build() {
        while (true) {
            console.println(ConsoleColors.toColor("Выберите тип отделки из доступных вариантов:", ConsoleColors.GREEN));
            console.println(Furnish.names());
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Furnish.valueOf(input);
            } catch (IllegalArgumentException e) {
                console.printError("Выбран недопустимый тип отделки. Пожалуйста, выберите из предложенных вариантов.");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
