package org.example.models;
import java.io.Serializable;
import java.util.Objects;

public class Coordinates implements Validator,Serializable {
    private double x;
    private Integer y; //Поле не может быть null

    public Coordinates(double x, Integer y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }

    @Override
    public boolean validate() {
        if (this.y == null) return false;
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return Double.compare(that.x, x) == 0 && y.equals(that.y);
    }
    @Override
    public int hashCode() {
        return Objects.hash(x, y);}

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }
}