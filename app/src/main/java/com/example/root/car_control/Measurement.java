package com.example.root.car_control;

import com.google.android.gms.maps.model.LatLng;

import java.util.Date;

/**
 * Created by billios on 19/8/2017.
 */

public class Measurement {
    private String vehicleId;
    private String routeId;
    private Date dateTime;
    private Integer coolanTemperature;
    private Integer mafPressure;
    private Integer rpm;
    private Integer speed;
    private Integer throttle;
    private LatLng coordinates;

    public Measurement(){

    }

    public Measurement(String vehicleId, String routeId, Date dateTime, Integer coolanTemperature, Integer mafPressure, Integer rpm, Integer speed, Integer throttle, LatLng coordinates) {
        this.vehicleId = vehicleId;
        this.routeId = routeId;
        this.dateTime = dateTime;
        this.coolanTemperature = coolanTemperature;
        this.mafPressure = mafPressure;
        this.rpm = rpm;
        this.speed = speed;
        this.throttle = throttle;
        this.coordinates = coordinates;
    }

    public String getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(String vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public Integer getCoolanTemperature() {
        return coolanTemperature;
    }

    public void setCoolanTemperature(Integer coolanTemperature) {
        this.coolanTemperature = coolanTemperature;
    }

    public Integer getMafPressure() {
        return mafPressure;
    }

    public void setMafPressure(Integer mafPressure) {
        this.mafPressure = mafPressure;
    }

    public Integer getRpm() {
        return rpm;
    }

    public void setRpm(Integer rpm) {
        this.rpm = rpm;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getThrottle() {
        return throttle;
    }

    public void setThrottle(Integer throttle) {
        this.throttle = throttle;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(LatLng coordinates) {
        this.coordinates = coordinates;
    }
}

