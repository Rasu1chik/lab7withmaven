package org.example.models;
import org.example.utility.OutputColors;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Класс квартиры, содержащий информацию о ней.
 */

public class Flat implements Serializable,Validator,Comparable<Flat> {
    private long id; // Уникальный идентификатор квартиры
    private String name; // Название квартиры
    private Coordinates coordinates; // Координаты квартиры
    private LocalDateTime creationDate; // Дата создания записи о квартире
    private double area; // Площадь квартиры
    private Long numberOfRooms; // Количество комнат
    private Furnish furnish; // Отделка квартиры
    private View view; // Вид из окон
    private Transport transport; // Доступность транспорта
    private House house; // Дом, в котором находится квартира
    private String userLogin;
    private static int nextId = 1;
    public void setUserLogin(String userLogin) {
        this.userLogin = userLogin;
    }

    public String getUserLogin() {
        return userLogin;
    }

    /**
     * Конструктор класса Flat.
     *
     * @param id
     * @param name          Название квартиры.
     * @param coordinates   Координаты квартиры.
     * @param area          Площадь квартиры.
     * @param numberOfRooms Количество комнат.
     * @param furnish       Отделка квартиры.
     * @param view          Вид из окон.
     * @param transport     Доступность транспорта.
     * @param house         Дом, в котором находится квартира.
     */
    public Flat(long id, String name, Coordinates coordinates, Timestamp creationDate, double area, Long numberOfRooms, Furnish furnish, View view, Transport transport, House house,String ownerLogin) {
        this.id = id; // Автоматическая генерация уникального ID
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate.toLocalDateTime(); // Автоматическая установка даты создания
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish = furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;
        this.userLogin = ownerLogin;
    }

    public Flat(String name, Coordinates coordinates, LocalDateTime creationDate, double area, Long numberOfRooms, Furnish furnish, View view, Transport transport, House house) {
        this.id = incNextId();
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.area = area;
        this.numberOfRooms = numberOfRooms;
        this.furnish= furnish;
        this.view = view;
        this.transport = transport;
        this.house = house;


    }

    private static Long incNextId(){
        return (long )nextId++;
    }

    // Геттеры и сеттеры для доступа к полям класса
    public void setId(long id){this.id = id;}
    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public Long getNumberOfRooms() {
        return numberOfRooms;
    }

    public void setNumberOfRooms(Long numberOfRooms) {
        this.numberOfRooms = numberOfRooms;
    }

    public Furnish getFurnish() {
        return furnish;
    }

    public void setFurnish(Furnish furnish) {
        this.furnish = furnish;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }


    public House getHouse() {
        return house;
    }



    public void setHouse(House house) {
        this.house = house;
    }


    @Override
    public boolean validate() {
        if (id <= 0) return false;
        if (name == null || name.isEmpty()) return false;
        if (coordinates == null) return false;
        if (creationDate == null) return false;
        if (area <= 0) return false;
        if (numberOfRooms == null || numberOfRooms <= 0) return false;
        if (view == null || transport == null) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Flat flat = (Flat) o;
        return id == flat.id &&
                Double.compare(flat.area, area) == 0 &&
                Objects.equals(name, flat.name) &&
                Objects.equals(coordinates, flat.coordinates) &&
                Objects.equals(creationDate, flat.creationDate) &&
                Objects.equals(numberOfRooms, flat.numberOfRooms) &&
                Objects.equals(furnish, flat.furnish) &&
                view == flat.view &&
                transport == flat.transport &&
                Objects.equals(house, flat.house);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coordinates, creationDate, area, numberOfRooms, furnish, view, transport, house);
    }

    @Override
    public String toString() {
        return "Flat{" +'\n'+
                OutputColors.toColor("id: ", OutputColors.GREEN) + id + '\n' +
                OutputColors.toColor("name: ", OutputColors.GREEN) + name + '\n' +
                OutputColors.toColor("coordinates: ", OutputColors.GREEN) + coordinates + '\n' +
                OutputColors.toColor("creationDate: ", OutputColors.GREEN) + creationDate + '\n' +
                OutputColors.toColor("area: ", OutputColors.BLUE) + area + '\n' +
                OutputColors.toColor("numberOfRooms ", OutputColors.BLUE) + numberOfRooms + '\n' +
                OutputColors.toColor("furnish: ", OutputColors.BLUE) + furnish + '\n' +
                OutputColors.toColor("view: ", OutputColors.BLUE) + view + '\n' +
                OutputColors.toColor("transport: ", OutputColors.BLUE) + transport + '}' + '\n' +
                OutputColors.toColor("house_name = ", OutputColors.RED) + house.getName() + '\n'+
                OutputColors.toColor("house_year = ", OutputColors.RED) + house.getYear() + '\n'+
                OutputColors.toColor("house_numberOfFloors = ", OutputColors.RED) + house.getNumberOfFloors() + '\n'+
                OutputColors.toColor("house_numberOfFlatsFloor = ", OutputColors.RED) + house.getNumberOfFlatsOnFloor() + '\n'+
                OutputColors.toColor("userLogin = ", OutputColors.RED) + userLogin + '\n';
    }



    @Override
    public  int compareTo(Flat flat) {
        return name.compareTo(flat.getName());
    }


}
