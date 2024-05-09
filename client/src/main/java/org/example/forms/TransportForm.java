package org.example.forms;

import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.models.Transport;
import org.example.utility.ExecuteFileManager;
import org.example.utility.OutputColors;
import java.util.Locale;

/**
 * Форма для выбора транспорта
 */
public class TransportForm extends Form<Transport> {
    private final Printable console;
    private final ConsoleInput scanner;

    public TransportForm(Printable console) {
        this.console = (ConsoleOutput.isFileMode()) ? new PrintConsole() : console;
        this.scanner = (ConsoleOutput.isFileMode()) ? new ExecuteFileManager() : new ConsoleInput();
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
            console.println(OutputColors.toColor("Введите вариант транспорта: ", OutputColors.GREEN));
            String input = scanner.nextLine().trim();
            try {
                return Transport.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception) {
                console.printError("Такого варианта транспорта нет в списке");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
