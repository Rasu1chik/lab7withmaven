package org.example.forms;


import org.example.commandLine.*;
import org.example.exeptions.ExceptionInFileMode;
import org.example.network.User;
import org.example.utility.ExecuteFileManager;
import org.example.utility.OutputColors;

import java.util.Objects;

/**
 * Форма для создания юзера
 */
public class UserForm extends Form<User> {

    private final Printable console;
    private final UserInput scanner;

    public UserForm(Printable console) {
        this.console = (ConsoleOutput.isFileMode())
                ? new PrintConsole()
                : console;
        this.scanner = (ConsoleOutput.isFileMode())
                ? new ExecuteFileManager()
                : new ConsoleInput();
    }

    /**
     * Сконструировать новый элемент класса {@link org.example.models.Flat}
     *
     * @return объект класса {@link org.example.models.Flat}
     */
    @Override
    public User build() {
        return new User(
                askLogin(),
                askPassword()
        );
    }

    public boolean askIfLogin() {
        for (; ; ) {
            console.print("У вас уже есть аккаунт? [y/n]  ");
            String input = scanner.nextLine().trim().toLowerCase();
            switch (input) {
                case "y", "yes", "да", "д" -> {
                    return true;
                }
                case "n", "no", "нет", "н" -> {
                    return false;
                }
                default -> console.printError("Ответ не распознан");
            }
        }
    }

    private String askLogin() {
        String login;
        while (true) {
            console.println(OutputColors.toColor("Введите ваш логин", OutputColors.GREEN));
            login = scanner.nextLine().trim();
            if (login.isEmpty()) {
                console.printError("Логин не может быть пустым");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
            } else {
                return login;
            }
        }
    }

    private String askPassword() {
        String pass;
        while (true) {
            console.println(OutputColors.toColor("Введите пароль", OutputColors.GREEN));
            pass = (Objects.isNull(System.console()))
                    ? scanner.nextLine().trim()
                    : new String(System.console().readPassword());
            if (pass.isEmpty()) {
                console.printError("Пароль не может быть пустым");
                if (ConsoleOutput.isFileMode()) throw new ExceptionInFileMode();
            } else {
                return pass;
            }
        }
    }
}
