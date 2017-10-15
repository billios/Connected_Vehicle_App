package com.example.root.car_control;

/**
 * Created by billios on 19/8/2017.
 */

public class Vehicle {
    private String id;
    private String brand;
    private String ownerFirstName;
    private String ownerLastName;

    public Vehicle(String id, String brand, String ownerFirstName, String ownerLastName) {
        this.id = id;
        this.brand = brand;
        this.ownerFirstName = ownerFirstName;
        this.ownerLastName = ownerLastName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getOwnerFirstName() {
        return ownerFirstName;
    }

    public void setOwnerFirstName(String ownerFirstName) {
        this.ownerFirstName = ownerFirstName;
    }

    public String getOwnerLastName() {
        return ownerLastName;
    }

    public void setOwnerLastName(String ownerLastName) {
        this.ownerLastName = ownerLastName;
    }
}
