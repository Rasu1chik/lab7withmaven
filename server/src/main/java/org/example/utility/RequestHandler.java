package org.example.utility;


import org.example.exeptions.*;
import org.example.managers.CommandManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;

public class RequestHandler {

    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public Response bufferError(){

        return new Response(ResponseStatus.ERROR,
                "Данные не влезают в буфер на сервере");
    }

    public Response handle(Request request) {
        try {
            commandManager.addToHistory(request.getCommandName());
            return commandManager.execute(request);
        } catch (IllegalArgumentsException e) {
            return new Response(ResponseStatus.WRONG_ARGUMENTS,
                    "Неверное использование аргументов команды");
        } catch (CommandRuntimeError e) {
            return new Response(ResponseStatus.ERROR,
                    "Ошибка при исполнении программы");
        } catch (NoSuchCommand e) {
            return new Response(ResponseStatus.ERROR, "Такой команды нет в списке");
        } catch (ExitObliged e) {
            return new Response(ResponseStatus.EXIT);
        } catch (InvalidForm e) {
            throw new RuntimeException(e);
        }
    }
}
