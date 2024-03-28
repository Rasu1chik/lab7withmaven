package org.example.models;

import java.util.Objects;

public class House implements Validator {
    private String name; //Поле может быть null
    private long year; //Значение поля должно быть больше 0
    private Long numberOfFloors; //Значение поля должно быть больше 0
    private Integer numberOfFlatsOnFloor; //Значение поля должно быть больше 0

    public House(String name, long year, Long numberOfFloors, Integer numberOfFlatsOnFloor) {
        this.name = name;
        this.year = year;
        this.numberOfFloors = numberOfFloors;
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public long getYear() {
        return year;
    }
    public void setYear(long year) {
        this.year = year;
    }
    public Long getNumberOfFloors() {
        return numberOfFloors;
    }
    public void setNumberOfFloors(Long numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }
    public Integer getNumberOfFlatsOnFloor() {
        return numberOfFlatsOnFloor;
    }
    public void setNumberOfFlatsOnFloor(Integer numberOfFlatsOnFloor) {
        this.numberOfFlatsOnFloor = numberOfFlatsOnFloor;
    }

    // Метод для валидации значений полей
    @Override
    public boolean validate() {
        if (year <= 0) return false;
        if (numberOfFloors != null && numberOfFloors <= 0) return false;
        if (numberOfFlatsOnFloor != null && numberOfFlatsOnFloor <= 0) return false;
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        House house = (House) o;

        if (!Objects.equals(numberOfFloors, house.numberOfFloors))
            return false;
        if (!Objects.equals(numberOfFlatsOnFloor, house.numberOfFlatsOnFloor))
            return false;
        if (!Objects.equals(name, house.name)) {
            return false;
        }
        return year == house.year;
    }
    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (int) (year ^ (year >>> 32));
        result = 31 * result + (numberOfFloors != null ? numberOfFloors.hashCode() : 0);
        result = 31 * result + (numberOfFlatsOnFloor != null ? numberOfFlatsOnFloor.hashCode() : 0);
        return result;
    }

}