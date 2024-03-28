package org.example.models.forms;

import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.models.Transport;

import java.util.Locale;

/**
 * Форма для выбора транспорта
 */
public class TransportForm extends Form<Transport> {
    private final Printable console;
    private final UserInput scanner;

    public TransportForm(Printable console) {
        this.console = (Console.isFileMode()) ? new BlankConsole() : console;
        this.scanner = (Console.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link Transport}
     *
     * @return объект класса {@link Transport}
     */
    @Override
    public Transport build() {
        console.println("Возможные варианты транспорта: ");
        console.println(Transport.names());
        while (true) {
            console.println(ConsoleColors.toColor("Введите вариант транспорта: ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Transport.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                console.printError("Такого варианта транспорта нет в списке");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
