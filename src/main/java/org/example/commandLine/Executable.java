package org.example.commandLine;

import org.example.exeptions.CommandRuntimeError;
import org.example.exeptions.ExitObliged;
import org.example.exeptions.IllegalArguments;

/**
 * Интерфейс для исполняемых команд
 */
public interface Executable {
    void execute(String args) throws CommandRuntimeError, ExitObliged, IllegalArguments;
}
