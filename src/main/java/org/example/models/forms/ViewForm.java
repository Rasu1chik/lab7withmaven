package org.example.models.forms;

import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.models.View;

import java.util.Locale;

/**
 * Форма для выбора вида
 */
public class ViewForm extends Form<View>{
    private final Printable console;
    private final UserInput scanner;
    private final String type;

    public ViewForm(Printable console, String type) {
        this.console = (Console.isFileMode())
                ? new BlankConsole()
                : console;
        this.type = type;
        this.scanner = (Console.isFileMode())
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
            console.println(ConsoleColors.toColor("Введите вид " + type + ": ", ConsoleColors.GREEN));
            String input = scanner.nextLine().trim();
            try{
                return View.valueOf(input.toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException exception){
                console.printError("Такого вида нет в списке");
                if (Console.isFileMode()) throw new ExceptionInFileMode();
            }
        }
    }
}
