package org.example.forms;

import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.models.Furnish;
import org.example.utility.ExecuteFileManager;
import org.example.utility.OutputColors;
/**
 * Форма для выбора типа отделки
 */
public class FurnishForm extends Form<Furnish> {
    private final Printable console;
    private final ConsoleInput scanner;

    public FurnishForm(Printable console) {
        this.console = (ConsoleOutput.isFileMode()) ? new PrintConsole() : console;
        this.scanner = (ConsoleOutput.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }

    /**
     * Строит новый элемент перечисления {@link Furnish}
     *
     * @return объект перечисления {@link Furnish}
     */
    @Override
    public Furnish build() {
        while (true) {
            console.println(OutputColors.toColor("Выберите тип отделки из доступных вариантов:", OutputColors.GREEN));
            console.println(Furnish.names());
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Furnish.valueOf(input);
            } catch (IllegalArgumentException e) {
                console.printError("Выбран недопустимый тип отделки. Пожалуйста, выберите из предложенных вариантов.");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
