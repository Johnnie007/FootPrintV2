package com.carbonTracker.footprint.model.vehicle;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class Vehicle {

    @JsonProperty("id")
    int id;
    @NotBlank
    @JsonProperty("type")
    String type;
    @NotBlank
    @JsonProperty("mpg")
    String mpg;


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

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", make='" + type + '\'' +
                ", mpg='" + mpg + '\'' +
                ", userId=" + userId +
                '}';
    }
}

