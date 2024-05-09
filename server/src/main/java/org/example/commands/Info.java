package org.example.commands;

import org.example.managers.*;
import org.example.exeptions.IllegalArgumentsException;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.utility.OutputColors;

/**
 * Команда 'info'
 * Выводит в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)
 */
public class Info extends Command{
    private CollectionManager collectionManager;


    public Info(CollectionManager collectionManager) {
        super("info", ": вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)");
        this.collectionManager = collectionManager;

    }

    /**
     * Исполнить команду
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();

        String lastInitTime = (collectionManager.getInitTimeInDate() == null)
                ? "В сессии коллекция не инициализирована"
                : collectionManager.getInitTimeInDate().toString();
        String lastSaveTime = (collectionManager.getLastSaveTime() == null)
                ? "В сессии коллекция не инициализирована "
                : collectionManager.getLastSaveTime().toString();
        String stringBuilder = ("Сведения о коллекции: \n" +
                OutputColors.toColor("Тип: ", OutputColors.GREEN) + collectionManager.getCollectionType() + "\n" +
                OutputColors.toColor("Количество элементов: ", OutputColors.GREEN) + collectionManager.getCollectionSize() + "\n" +
                OutputColors.toColor("Дата последней инициализации: ", OutputColors.GREEN) + lastInitTime + "\n" +
                OutputColors.toColor("Дата последнего изменения: ", OutputColors.GREEN) + lastSaveTime + "\n");
        return new Response(ResponseStatus.OK, stringBuilder);
    }
}
