package com.carbonTracker.footprint.model.footprint;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.offsetters.Offsetters;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;

import java.util.List;

public class Footprint {
    User user;
    List <Vehicle> vehicles;
    List <Home> homes;

    List <Offsetters> offsetters;



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    public List<Home> getHomes() {
        return homes;
    }

    public void setHomes(List<Home> homes) {
        this.homes = homes;
    }

    public List<Offsetters> getOffsetters(){
        return offsetters;
    }
    public void setOffsetters(List<Offsetters> offsetters){
        this.offsetters= offsetters;
    }

}
