package org.example.commands;


import org.example.forms.FlatForm;
import org.example.commandLine.Printable;
import org.example.exeptions.IllegalArgumentsException;
import org.example.models.Flat;

public class Add extends Command {
    private final Printable console;


    public Add(Printable console) {
        super("add", "добавление элемента");
        this.console = console;
    }


    public Flat execute(String args) throws IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        return new FlatForm(console).build();
    }


}
