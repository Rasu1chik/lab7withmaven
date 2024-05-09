package org.example.managers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.commands.CollectionEditor;
import org.example.commands.Command;
import org.example.exeptions.*;
import org.example.network.Request;
import org.example.network.Response;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Командный менеджер.
 * Реализует паттерн программирования Command
 */
public class CommandManager {

    /**
     * Поле для хранения комманд в виде Имя-Комманда
     */

    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Поле для истории команд
     */
    private final List<String> commandHistory = new ArrayList<>();

    private final FileManager fileManager;

    static final Logger commandManagerLogger = LogManager.getLogger(CommandManager.class);

    public CommandManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }




    public void addCommand(Collection<Command> commands) {
        this.commands.putAll(commands.stream().collect(Collectors.toMap(Command::getName, s -> s)));
        commandManagerLogger.info("Добавлены комманды", commands);

    }


    public Collection<Command> getCommands() {
        return commands.values();
    }

    public Set<String> getCommandsNames() {
        return commands.keySet();

    }

    public void addToHistory(String line) {
        if (line.isBlank()) return;
        this.commandHistory.add(line);
        commandManagerLogger.info("Добавлена команда в историю: " + line, line);
    }

    public List<String> getCommandHistory() {
        return commandHistory;
    }

    /**
     * Выполняет команду
     *
     * @throws NoSuchCommand        такая команда отсутствует
     * @throws IllegalArgumentsException неверные аргументы команды
     * @throws CommandRuntimeError команда выдала ошибку при исполнении
     * @throws ExitObliged       команда вызвала выход из программы
     */

    public Response execute(Request request) throws NoSuchCommand, IllegalArgumentsException, ExitObliged, CommandRuntimeError, InvalidForm {
        Command command = commands.get(request.getCommandName());
        if (command == null) {
            commandManagerLogger.fatal("Нет такой команды " + request.getCommandName());
            throw new NoSuchCommand();
        }
        Response response = command.execute(request);
        commandManagerLogger.info("Выполнение команды ", response);
        if (command instanceof CollectionEditor) {
            commandManagerLogger.info("Файл обновлен");
            fileManager.saveObjects();
        }
        return response;
    }


}
