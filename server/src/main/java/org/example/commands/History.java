package org.example.commands;


import org.example.exeptions.IllegalArgumentsException;
import org.example.managers.CommandManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.utility.OutputColors;

import java.util.List;


/**
 * Команда 'history'
 * Выводит последние 5 команд (без их аргументов)
 */
public class History extends Command{
    private CommandManager commandManager;


    public History(CommandManager commandManager) {
        super("history", " вывести последние 7 команд (без их аргументов)");
        this.commandManager = commandManager;

    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        List<String> history= commandManager.getCommandHistory();
        return new Response(ResponseStatus.OK,String.join("\n",history.subList(Math.max(history.size() - 7 , 0),history.size())));
    }
}
