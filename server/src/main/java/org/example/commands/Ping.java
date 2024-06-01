package org.example.commands;


import org.example.exeptions.IllegalArgumentsException;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;

/**
 * Команда 'ping'
 * пингануть сервак
 */
public class Ping extends Command {
    public Ping() {
        super("ping", ": пингануть сервер");
    }

    /**
     * Исполнить команду
     *
     * @param request запрос клиента
     * @throws org.example.exeptions.IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        return new Response(ResponseStatus.OK, "pong");
    }
}
