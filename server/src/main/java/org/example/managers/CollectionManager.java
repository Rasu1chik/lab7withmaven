package org.example.managers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.models.Flat;
import org.example.exeptions.InvalidForm;
import org.example.utility.DatabaseHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TreeSet;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Класс, организующий работу с коллекцией.
 */
public class CollectionManager {

    private final Set<Flat> collection = new TreeSet<>();


    private final LocalDateTime lastInitTime;
    private LocalDateTime lastSaveTime;
    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock writeLock = lock.writeLock();
    Lock readLock = lock.readLock();
    private static final Logger collectionManagerLogger = LogManager.getLogger(CollectionManager.class);


    /**
     * Конструктор класса CollectionManager.
     * Инициализирует дату создания коллекции.
     */
    public CollectionManager() {
        this.lastInitTime = LocalDateTime.now();
        this.lastSaveTime = null;
        collection.addAll(DatabaseHandler.getDatabaseManager().loadCollection());
    }
    public Set<Flat> getCollection() {
        try {
            readLock.lock();
            return collection;
        } finally {
            readLock.unlock();
        }
    }

    public static String formatTime(LocalDateTime localDateTime) {
        if (localDateTime == null) return null;
        return localDateTime.format(DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss"));
    }


    public String reversCollections(Set<Flat> revers){
        return String.valueOf(revers.toString());
    }

    // Вспомогательные методы для будущих команд

    public void clear(){
        this.collection.clear();

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
        if (collection.isEmpty()) {
            return null;
        }
        Flat last = null;
        for (Flat flat : collection) {
            last = flat;
        }
        return last;
    }

    public void removeElement(Flat el){
        this.lastSaveTime = LocalDateTime.now();
        collection.remove(el);
    }

    public void removeElements(Collection<Flat> els){
        this.lastSaveTime = LocalDateTime.now();
        this.collection.removeAll(els);
    }

    public void addElement(Flat flat) {
        try {
            writeLock.lock();
            this.lastSaveTime = LocalDateTime.now();
            collection.add(flat);
            collectionManagerLogger.info("Добавлен объект в коллекцию", flat);
        } finally {
            writeLock.unlock();
        }
    }

    public void addElements(Collection<Flat> els) throws InvalidForm{
        if (els != null) {
            collection.addAll(els);
        }
    }

    public Collection<Flat> filterByHouse(String houseName, long year, Long numberOfFloors, Integer numberOfFlatsOnFloor) {
        // Используем стримы для фильтрации элементов по заданному значению поля house
        Collection<Flat> filteredFlats = new ArrayList<>();
        for (Flat flat : collection) {
            if (flat.getHouse().getName().equals(houseName) &&
                    flat.getHouse().getYear() == year &&
                    flat.getHouse().getNumberOfFloors() == numberOfFloors &&
                    flat.getHouse().getNumberOfFlatsOnFloor() == numberOfFlatsOnFloor) {
                filteredFlats.add(flat);
            }
        }

        return filteredFlats;
    }

    /**
     * Изменить элемент коллекции с таким id
     * @param id id объекта
     * @param newElement новый элемент
     * @throws InvalidForm Нет элемента с таким id
     */
    public void editById(int id, Flat newElement) throws InvalidForm {
        Flat pastElem = this.getById(id);
        if (pastElem == null) {
            throw new InvalidForm();
        }
        this.removeElement(pastElem);
        newElement.setId(id);
        this.addElement(newElement);
        this.lastSaveTime = LocalDateTime.now();
    }



    public String getLastSaveTime(){
        return formatTime(lastSaveTime);
    }

    public LocalDateTime getInitTimeInDate(){
        return lastInitTime;
    }

    public LocalDateTime getLastSaveTimeInDate() {
        return lastSaveTime;
    }

    public void setLastSaveTime(LocalDateTime lastSaveTime){
        this.lastSaveTime = lastSaveTime;
    }
    public Long getLastId() {
        return getCollection().stream()
                .mapToLong(Flat::getId)
                .max()
                .orElse(0);
    }




    public String getCollectionType(){
        return collection.getClass().getName();
    }

    public int getCollectionSize(){
        return collection.size();
    }

    @Override
    public String toString() {
        if (collection.isEmpty()) return "Коллекция пустая";

        Flat last = getLastEl();

        StringBuilder info = new StringBuilder();
        for (Flat flat: collection){
            info.append(flat);
            if (flat != last) return "\n\n";
        }
        return info.toString();
    }
}