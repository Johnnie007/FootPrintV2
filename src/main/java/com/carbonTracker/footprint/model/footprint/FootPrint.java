package com.carbonTracker.footprint.model.footprint;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.recommendationList.RecommendationList;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class FootPrint {

    @NotBlank()
    @JsonProperty("id")
    private int id;

    private User user;

    private Vehicle vehicle;

    private Home home;

    private RecommendationList recommendationList;

    public FootPrint(int id){
        this.id = id;
//        this.vehicle = vehicle;
//        this.home = home;
//        this.recommendationList = recommendationList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

//    public Home getHome() {
//        return home;
//    }
//
//    public User getUser() {
//        return user;
//    }
//
//    public Vehicle getVehicle() {
//        return vehicle;
//    }

//    public RecommendationList getRecommendationList() {
//        return recommendationList;
//    }



//    public void setUser(User user) {
//        this.user = user;
//    }
//
//    public void setHome(Home home) {
//        this.home = home;
//    }
//
//    public void setVehicle(Vehicle vehicle) {
//        this.vehicle = vehicle;
//    }
//
//    public void setRecommendationList(RecommendationList recommendationList) {
//        this.recommendationList = recommendationList;
//    }
}
