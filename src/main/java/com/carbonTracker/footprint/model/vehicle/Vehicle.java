package com.carbonTracker.footprint.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Vehicle {

    @JsonProperty("id")
    int id;
    @NotBlank
    @JsonProperty("type")
    String type;
    @NotBlank
    @JsonProperty("mpg")
    String mpg;

    @NotNull
    @JsonProperty("vehicleGHG")
    private double vehicleGHG;
    @JsonProperty("userId")
    int userId;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMpg() {
        return mpg;
    }

    public void setMpg(String mpg) {
        this.mpg = mpg;
    }

    public int getUserId() {
        return userId;
    }

    public int setUserId(int userId){
       return this.userId = userId;
    }

    public double getVehicleGHG() {
        return vehicleGHG;
    }

    public void setVehicleGHG(double vehicleGHG) {
        this.vehicleGHG = vehicleGHG;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + type + '\'' +
                ", mpg='" + mpg + '\'' +
                ", vehicleGHG='" + vehicleGHG + '\'' +
                ", userId=" + userId +
                '}';
    }


}

