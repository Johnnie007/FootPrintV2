package com.carbonTracker.footprint.model.GHGStorage;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class GHGStorage {

    @JsonProperty("id")
    private int id;

    @NotNull
    @JsonProperty("vehicleTotal")
    private double vehicleTotal;

    @NotNull
    @JsonProperty("homeTotal")
    private double homeTotal;

    @NotBlank
    @JsonProperty("storageMonth")
    private String storageMonth;


    @NotNull
    @JsonProperty("userId")
    private int userId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getVehicleTotal() {
        return vehicleTotal;
    }

    public void setVehicleTotal(double vehicleTotal) {
        this.vehicleTotal = vehicleTotal;
    }

    public double getHomeTotal() {
        return homeTotal;
    }

    public void setHomeTotal(double homeTotal) {
        this.homeTotal = homeTotal;
    }

    public String getStorageMonth() {
        return storageMonth;
    }

    public void setStorageMonth(String storageMonth) {
        this.storageMonth = storageMonth;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

}