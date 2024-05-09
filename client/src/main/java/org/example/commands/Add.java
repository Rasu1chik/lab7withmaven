package org.example.commands;


import org.example.forms.FlatForm;
import org.example.commandLine.Printable;
import org.example.exeptions.IllegalArgumentsException;
import org.example.exeptions.InvalidForm;
import org.example.models.Flat;

public class Add extends Command {
    private final Printable console;


    public Add(Printable console) {
        super("add", "создает");
        this.console = console;
    }


    public Flat execute(String args) throws InvalidForm, IllegalArgumentsException {
        if (!args.isBlank()) throw new IllegalArgumentsException();
        Flat flat = new FlatForm(console).build();
        if (!flat.validate()) throw new InvalidForm();
        return flat;
    }


}
