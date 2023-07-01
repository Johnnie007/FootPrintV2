package com.carbonTracker.footprint.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class Vehicle {

    @JsonProperty("id")
    int id;
    @NotBlank
    @JsonProperty("make")
    String make;
    @NotBlank
    @JsonProperty("model")
    String model;

    @JsonProperty("year")
    int year;

    @JsonProperty("userId")
    int userId;

    public Vehicle(int id, String make, String model, int year, int userId){
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear(){
        return year;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public int setUserId(int userId){
       return this.userId = userId;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", userId=" + userId +
                '}';
    }
}

