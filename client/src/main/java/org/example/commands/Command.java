package org.example.commands;

import org.example.models.Flat;
import org.example.exeptions.IllegalArgumentsException;
import org.example.exeptions.InvalidForm;
import org.example.models.Flat;

import java.util.Objects;

public abstract class Command {

    private final String name;

    private final String description;


    public Command(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }


    public boolean equals(Object o) {
        if (o == this) return true;
        if(o == null || o.getClass() != this.getClass()) return false;
        Command command = (Command) o;
        return (Objects.equals(name, command.name) && Objects.equals(description, command.description));
    }


    public int hashCode(){
        return Objects.hash(name, description);

    }

    public String toString(){
        return name + " " + description;
    }

    public abstract Flat execute(String args) throws InvalidForm, IllegalArgumentsException;



}