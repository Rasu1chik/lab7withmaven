package org.example.models;

public enum View {
    STREET,
    BAD,
    NORMAL,
    GOOD,
    TERRIBLE;

    /**
     * @return перечисляет в строке все элементы Enum
     */
    public static String names() {
        StringBuilder nameList = new StringBuilder();
        for (var viev : values()) {
            nameList.append(viev.name()).append("\n");
        }
        return nameList.substring(0, nameList.length()-1);
    }
}
