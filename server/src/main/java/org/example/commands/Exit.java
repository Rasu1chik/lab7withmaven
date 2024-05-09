package org.example.commands;

import org.example.exeptions.ExitObliged;
import org.example.network.Request;
import org.example.network.Response;

/**
 * Команда 'exit'
 * завершить программу (без сохранения в файл)
 */
public class Exit extends Command{
    public Exit(){
        super("exit", ": завершить программу (без сохранения в файл)");
    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws ExitObliged нужен выход из программы
     */
    @Override
    public Response execute(Request request) throws ExitObliged{
        throw new ExitObliged();
    }
}
