package org.example.commandLine;

import org.example.utility.OutputColors;

/**
 * Класс Console представляет собой реализацию интерфейса Printable
 * и предоставляет методы для вывода текста в консоль.
 */
public class ConsoleOutput implements Printable {

    private static boolean fileMode = false;

    /**
     * Проверяет, установлен ли режим вывода в файл.
     *
     * @return true, если установлен режим вывода в файл, в противном случае - false
     */
    public static boolean isFileMode() {
        return fileMode;
    }

    /**
     * Устанавливает режим вывода в файл.
     *
     * @param fileMode true для установки режима вывода в файл, false для отключения
     */
    public static void setFileMode(boolean fileMode) {
        ConsoleOutput.fileMode = fileMode;
    }

    /**
     * Выводит строку с переводом строки в консоль.
     *
     * @param text строка для вывода
     */
    @Override
    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Выводит строку без перевода строки в консоль.
     *
     * @param text строка для вывода
     */
    @Override
    public void print(String text) {
        System.out.print(text);
    }

    /**
     * Выводит строку об ошибке в красном цвете в консоль.
     *
     * @param error текст ошибки для вывода
     */
    @Override
    public void printError(String error) {
        System.out.println(OutputColors.RED + error + OutputColors.RESET);
    }
}
