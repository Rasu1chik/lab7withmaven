package org.example.commands;

import org.example.exeptions.ExceptionInFileMode;
import org.example.forms.FlatForm;
import org.example.commandLine.Printable;
import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArgumentsException;
import org.example.models.Flat;

public class AddIfMin extends Command{
    private final Printable console;

    public AddIfMin(Printable console) {
        super("add_if_min", "создает");
        this.console = console;
    }

    public Flat execute(String args) throws IllegalArgumentsException{
        if(args.isBlank()) throw new IllegalArgumentsException();
        Flat flat = new FlatForm(console).build();
        if (!flat.validate()) throw new ExceptionInFileMode();
        return flat;

    }
}
