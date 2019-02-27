package com.tbp.repository;

public class Car {

    Integer id;
    String brand;
    Integer year;
    String color;
    String name;


    public Car(String brand, Integer year, String color, String name) {
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
}
