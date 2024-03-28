package org.example.managers;

import org.example.commandLine.*;
import org.example.exeptions.CommandRuntimeError;
import org.example.exeptions.ExitObliged;
import org.example.exeptions.IllegalArguments;
import org.example.exeptions.NoSuchCommand;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс обработки пользовательского ввода
 */
public class RuntimeManager {
    private final Printable console;
    private final CommandManager commandManager;

    public RuntimeManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Перманентная работа с пользователем и выполнение команд
     */
    public void interactiveMode(){
        Scanner userScanner = ScannerManager.getUserScanner();
        while (true) {
            try{
                if (!userScanner.hasNext()) throw new ExitObliged();
                String userCommand = userScanner.nextLine().trim() + " ";
                String[] commanParts = userCommand.split(" ", 2);
                if(commanParts.length > 1){
                    commanParts[1] = commanParts[1].trim();
                }

                this.launch(commanParts);
                commandManager.addToHistory(userCommand);
            } catch (NoSuchElementException exception) {
                console.printError("Пользовательский ввод не обнаружен!");
            } catch (NoSuchCommand noSuchCommand) {
                console.printError("Такой команды нет в списке");
            } catch (IllegalArguments e) {
                console.printError("Введены неправильные аргументы команды");
            } catch (CommandRuntimeError e) {
                console.printError("Ошибка при исполнении команды");
            } catch (ExitObliged exitObliged){
                console.println(ConsoleColors.toColor("До свидания!", ConsoleColors.YELLOW));
                return;
            }
        }
    }

    /**
     * Триггер выполнения команды из {@link CommandManager}
     * @param userCommand массив из 2 элементов, первый - название команды, второй - аргументы
     * @throws NoSuchCommand несуществующая команда
     * @throws ExitObliged команда привела к окончанию работы программы
     * @throws IllegalArguments команда содержит неверные аргументы
     * @throws CommandRuntimeError команда выдала ошибку во время выполнения
     */
    public void launch(String[] userCommand) throws NoSuchCommand, ExitObliged, IllegalArguments, CommandRuntimeError {
        if (userCommand[0].equals("")) return;
        commandManager.execute(userCommand[0], userCommand[1]);
    }
}
