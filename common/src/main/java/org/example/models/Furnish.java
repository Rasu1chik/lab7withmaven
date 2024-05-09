package org.example.models;

import java.io.Serializable;

/**
 * Перечисление различных видов отделки
 */
public enum Furnish implements Serializable {
    DESIGNER,
    NONE,
    FINE,
    BAD,
    LITTLE;

    /**
     * @return строку, перечисляющую все элементы перечисления
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (Furnish furnish : values()) {
            nameList.append(furnish.name()).append("\n");
        }
        return nameList.substring(0, nameList.length() - 1);
    }
}
