package org.example.managers;

import org.example.models.Flat;
import org.example.exeptions.*;

import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Collectors;

/**
 * Класс организующий работу с коллекцией
 */
public class CollectionManager {

    private final TreeSet<Flat> collection = new TreeSet<Flat>();

    /**
     * Дата создания коллекции
     */
    private LocalDateTime lastInitTime;
    private final LocalDateTime initTime;
    /**
     * Дата последнего сохранения коллекции
     */
    private LocalDateTime lastSaveTime;
    public CollectionManager(){
        this.initTime = LocalDateTime.now();
        this.lastInitTime = null;
    }
    public static String formatTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
//        if (localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
//                .equals(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")))) {
//            return localDateTime.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//        }
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }
    public TreeSet<Flat> getCollection(){
        return collection;
    }
    public String reversCollections(TreeSet<Flat> revers){
        return String.valueOf(revers.descendingSet());
    }

    // Вспомогательные методы для будущий команд

    public void clear(){
        this.collection.clear();
        lastInitTime = LocalDateTime.now();
    }
    /**
     * @param id ID элемента.
     * @return Элемент по его ID или null, если не найдено.
     */
    public Flat getById(int id){
        for (Flat elem: collection){
            if (elem.getId() == id){
                return elem;
            }
        }
        return null;
    }
    /**
     * @param id ID элемента.
     * @return Проверяет, существует ли элемент с таким ID.
     */
    public boolean checkExist(int id){
        return collection.stream().anyMatch((x) -> x.getId() == id);
    }
    public Flat getLastEl(){
        return collection.last();
    }
    public void removeElement(Flat el){
        this.lastSaveTime = LocalDateTime.now();
        collection.remove(el);
    }
    public void removeElements(Collection<Flat> els){
        this.lastSaveTime = LocalDateTime.now();
        this.collection.removeAll(els);
    }
    public void addElement(Flat el) throws InvalidForm{
        this.lastInitTime = LocalDateTime.now();
        collection.add(el);
    }
    public void addElements(Collection<Flat> els) throws InvalidForm{
        lastInitTime = LocalDateTime.now();
        if (els == null){
            return;
        }
        collection.addAll(els);
    }
    public Collection<Flat> filterByHouse(String houseName, long year, Long numberOfFloors, Integer numberOfFlatsOnFloor) {
        // Используем стримы для фильтрации элементов по заданному значению поля house
        return collection.stream()
                .filter(flat -> flat.getHouse() != null && flat.getHouse().getName().equals(houseName))
                .collect(Collectors.toList());
    }
    /**
     * Изменить элемент коллекции с таким id
     * @param id id объекта
     * @param newElement новый элемент
     * @throws InvalidForm Нет элемента с таким id
     */
    public void editById(int id, Flat newElement) throws InvalidForm {
        Flat pastElem = this.getById(id);
        this.removeElement(pastElem);
        newElement.setId(id);
        this.addElement(newElement);
        Flat.updateId(this.getCollection());
        this.lastSaveTime = LocalDateTime.now();
    }
    public String getLastInitTime(){
        return formatTime(lastInitTime);
    }
    public String getLastSaveTime(){
        return formatTime(lastSaveTime);
    }
    public LocalDateTime getInitTimeInDate(){
        return initTime;
    }
    public LocalDateTime getLastSaveTimeInDate() {
        return lastSaveTime;
    }
    public void setLastSaveTime(LocalDateTime lastSaveTime){
        this.lastSaveTime = lastSaveTime;
    }
    public void setLastInitTime(LocalDateTime lastInitTime){
        this.lastInitTime = lastInitTime;
    }

    public String getCollectionType(){
        return collection.getClass().getName();
    }
    public int getCollectionSize(){
        return collection.size();
    }
    @Override
    public String toString() {
        if (collection.isEmpty()) return "Колекция пустая";

        var last = getLastEl();

        StringBuilder info = new StringBuilder();
        for (Flat flat: collection){
            info.append(flat);
            if (flat != last) return "\n\n";
        }
        return info.toString();
    }
}