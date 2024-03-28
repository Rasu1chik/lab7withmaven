package org.example.models;

/**
 * Перечисление различных вариантов транспорта
 */
public enum Transport {
    FEW,
    NONE,
    LITTLE,
    NORMAL,
    ENOUGH;

    /**
     * @return строку, содержащую имена всех элементов перечисления
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var transport : values()) {
            nameList.append(transport.name()).append("\n");
        }
        return nameList.substring(0, nameList.length() - 1);
    }
}
