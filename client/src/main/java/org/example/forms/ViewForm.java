package org.example.forms;

import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.models.View;
import org.example.utility.ExecuteFileManager;
import org.example.utility.OutputColors;
import java.util.Locale;

/**
 * Форма для выбора вида
 */
public class ViewForm extends Form<View>{
    private final Printable console;
    private final ConsoleInput scanner;
    private final String type;

    public ViewForm(Printable console, String type) {
        this.console = (ConsoleOutput.isFileMode())
                ? new PrintConsole()
                : console;
        this.type = type;
        this.scanner = (ConsoleOutput.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link View}
     * @return объект класса {@link View}
     */
    @Override
    public View build() {
        console.println("Возможные виды: ");
        console.println(View.names());
        while (true){
            console.println(OutputColors.toColor("Введите вид " + type + ": ", OutputColors.GREEN));
            String input = scanner.nextLine().trim();
            try{
                return View.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printError("Такого вида нет в списке");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
