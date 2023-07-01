package com.carbonTracker.footprint.model.home;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class Home {

    @NotBlank
    @JsonProperty("id")
    private int id;

    @NotBlank
    @JsonProperty("homeType")
    private String homeType;

    @NotBlank
    @JsonProperty("homeSize")
    private int homeSize;

    @NotBlank
    @JsonProperty("userId")
    private int userId;

    public Home(int id, String homeType, int homeSize, int userId){
        this.id = id;
        this.homeType = homeType;
        this.homeSize = homeSize;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public int getHomeSize() {
        return homeSize;
    }

    public String getHomeType(){
        return homeType;
    }

    public int getUserId(){
        return userId;
    }

    public void setHomeSize(int homeSize) {
        this.homeSize = homeSize;
    }

    public void setHomeType(String homeType) {
        this.homeType = homeType;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
