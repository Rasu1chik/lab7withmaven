package org.example.commands;

import org.example.managers.CollectionManager;
import org.example.network.Request;
import org.example.network.Response;
import org.example.network.ResponseStatus;
import org.example.utility.DatabaseHandler;

public class AddToDB {

    public static Response Add(Request request, CollectionManager collectionManager){
        int new_id = DatabaseHandler.getDatabaseManager().addObject(request.getObject(), request.getUser());
        if (new_id == -1) return new Response(ResponseStatus.ERROR, "Объект добавить не удалось");
        request.getObject().setId((long) new_id);
        request.getObject().setUserLogin(request.getUser().name());
        collectionManager.addElement(request.getObject());
        return new Response(ResponseStatus.OK, "Объект успешно добавлен");
    }
}
