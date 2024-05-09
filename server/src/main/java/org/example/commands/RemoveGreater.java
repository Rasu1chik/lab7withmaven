package org.example.commands;

import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArgumentsException;
import org.example.managers.CollectionManager;
import org.example.models.Flat;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда 'remove_greater'
 * Удаляет из коллекции все элементы, превышающие заданный
 */
public class RemoveGreater extends Command implements CollectionEditor{
    private final CollectionManager collectionManager;

    public RemoveGreater(CollectionManager collectionManager) {
        super("remove_greater", " {element} : удалить из коллекции все элементы, превышающие заданный");
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
        class NoElements extends RuntimeException{

        }
        try {
            if (Objects.isNull(request.getObject())){
                return new Response(ResponseStatus.ASK_OBJECT, "Для команды " + this.getName() + " требуется объект");
            }
            Collection<Flat> toRemove = collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .filter(flat -> flat.compareTo(request.getObject()) >= 1)
                    .toList();
            collectionManager.removeElements(toRemove);
            return new Response(ResponseStatus.OK,"Удалены элементы большие чем заданный");
        } catch (NoElements e){
            return new Response(ResponseStatus.ERROR,"В коллекции нет элементов");
        } catch (ExceptionInFileMode e){
            return new Response(ResponseStatus.ERROR,"Поля в файле не валидны! Объект не создан");
        }
    }
}