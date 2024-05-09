package org.example.commands;


import org.example.forms.FlatForm;
import org.example.commandLine.Printable;
import org.example.exeptions.ExceptionInFileMode;
import org.example.exeptions.IllegalArgumentsException;
import org.example.models.Flat;

public class Update extends Command{
    private Printable console;

    public Update(Printable console){
        super("update", "создает");
        this.console = console;
    }



    public Flat execute(String args) throws IllegalArgumentsException {
        if(args.isBlank()) throw new IllegalArgumentsException();
        Flat flat = new FlatForm(console).build();
        if (!flat.validate()) throw new ExceptionInFileMode();
        return flat;

    }
}
