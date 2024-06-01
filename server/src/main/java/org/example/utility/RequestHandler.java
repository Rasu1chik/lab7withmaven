package org.example.utility;



import org.example.exeptions.*;
import org.example.managers.CommandManager;
import org.example.managers.ConnectionManagerPool;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;

import java.io.ObjectOutputStream;
import java.util.concurrent.Callable;

public class RequestHandler implements Callable<ConnectionManagerPool> {
    private final CommandManager commandManager;
    private final Request request;
    private ObjectOutputStream objectOutputStream;

    public RequestHandler(CommandManager commandManager, Request request, ObjectOutputStream objectOutputStream) {
        this.commandManager = commandManager;
        this.request = request;
        this.objectOutputStream = objectOutputStream;
    }

    public ObjectOutputStream getObjectOutputStream() {
        return objectOutputStream;
    }

    public void setObjectOutputStream(ObjectOutputStream objectOutputStream) {
        this.objectOutputStream = objectOutputStream;
    }

    @Override
    public ConnectionManagerPool call() {
        try {
            commandManager.addToHistory(request.getUser(), request.getCommandName());
            return new ConnectionManagerPool(commandManager.execute(request), objectOutputStream);
        } catch (IllegalArgumentsException e) {
            return new ConnectionManagerPool(new Response(ResponseStatus.WRONG_ARGUMENTS,
                    "Неверное использование аргументов команды"), objectOutputStream);
        } catch (CommandRuntimeError e) {
            return new ConnectionManagerPool(new Response(ResponseStatus.ERROR,
                    "Ошибка при исполнении программы"), objectOutputStream);
        } catch (NoSuchCommand e) {
            return new ConnectionManagerPool(new Response(ResponseStatus.ERROR, "Такой команды нет в списке"), objectOutputStream);
        } catch (ExitObliged e) {
            return new ConnectionManagerPool(new Response(ResponseStatus.EXIT), objectOutputStream);
        } catch (InvalidForm e) {
            throw new RuntimeException(e);
        }
    }
}

