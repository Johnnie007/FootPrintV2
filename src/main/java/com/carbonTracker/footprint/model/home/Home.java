package com.carbonTracker.footprint.model.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Home {

    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("month_added")
    private String month;

    @NotBlank
    @JsonProperty("homeType")
    private String homeType;

    @NotNull
    @JsonProperty("homeSize")
    private int homeSize;

    @NotNull
    @JsonProperty("homeGHG")
    private double homeGHG;

    @NotNull
    @JsonProperty("userId")
    private int userId;


    public int getId() {
        return id;
    }

    public int getHomeSize() {
        return homeSize;
    }

    public String getHomeType(){
        return homeType;
    }
    public double getHomeGHG(){return homeGHG;}

    public int getUserId(){
        return userId;
    }

    public void setHomeSize(int homeSize) {
        this.homeSize = homeSize;
    }

    public void setHomeGHG(double homeGHG) {this.homeGHG = homeGHG;}
    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
