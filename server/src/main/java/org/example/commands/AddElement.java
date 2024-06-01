package org.example.commands;


import org.example.exeptions.IllegalArgumentsException;
import org.example.exeptions.InvalidForm;
import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;


/**
 * Команда 'add'
 * Добавляет новый элемент в коллекцию
 */
public class AddElement extends Command implements CollectionEditor {
    private final CollectionManager collectionManager;


    public AddElement(CollectionManager collectionManager) {
        super("add", " {element} : добавить новый элемент в коллекцию");
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнить команду
     *
     * @param request аргументы команды
     * @throws IllegalArgumentsException неверные аргументы команды
     */
    @Override
    public Response execute(Request request) throws IllegalArgumentsException, InvalidForm {
        if (!request.getArgs().isBlank()) throw new IllegalArgumentsException();
        if (request.getObject() == null) {
            return new Response(ResponseStatus.ERROR, "Не могу добавить обжект");
        }
//        int new_id = DatabaseHandler.getDatabaseManager().addObject(request.getObject(), request.getUser());
//        if (new_id == -1) return new Response(ResponseStatus.ERROR, "Объект добавить не удалось");
//        request.getObject().setId((long) new_id);
//        request.getObject().setUserLogin(request.getUser().name());
//        collectionManager.addElement(request.getObject());
//        return new Response(ResponseStatus.OK, "Объект успешно добавлен");
        return AddToDB.Add(request, collectionManager);

    }
}
