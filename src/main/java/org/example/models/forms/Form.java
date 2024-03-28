package org.example.models.forms;

import org.example.exeptions.InvalidForm;

/**
 * Абстрактный класс для пользовательских форм ввода
 * @param <T> класс формы
 */
public abstract class Form<T>{
    public abstract T build() throws InvalidForm;
}
