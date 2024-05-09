package org.example.commands;

import org.example.exeptions.IllegalArgumentsException;
import org.example.exeptions.InvalidForm;
import org.example.exeptions.NoSuchCommand;
import org.example.models.Flat;


import java.util.HashMap;

public class CommandManager {

    private final HashMap<String, Command> commands = new HashMap<>();


    public void addCommand(Command command) {
        commands.put(command.getName(), command);
    }

    public boolean containsCommand(String name){
        return commands.containsKey(name);
    }
    public Command getCommand(String name){
        return commands.get(name);
    }


    public Flat execute(Command command, String args) throws NoSuchCommand, InvalidForm, IllegalArgumentsException {
        Command command1 = commands.get(command.getName());
        if (command1 == null) {
            throw new NoSuchCommand();
        }
        return command1.execute(args);
    }
}