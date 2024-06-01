package org.example.commands;


import org.example.exeptions.CommandRuntimeError;
import org.example.exeptions.ExitObliged;
import org.example.exeptions.IllegalArgumentsException;
import org.example.network.Response;
import org.example.network.Request;
import org.example.network.ResponseStatus;

/**
 * Команда 'execute_script'
 * Считатывает и исполняет скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.
 */
public class Execute extends Command{

    public Execute() {
        super("execute_script", " file_name: считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.");
    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     * @throws CommandRuntimeError команда вызвала ошибку при исполнении
     * @throws ExitObliged требуется выход из программы
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (request.getArgs().isBlank()) throw new IllegalArgumentsException(
        );
        return new Response(ResponseStatus.EXECUTE_SCRIPT, request.getArgs());
    }

}
