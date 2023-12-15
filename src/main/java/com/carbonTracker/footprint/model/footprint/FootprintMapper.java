package com.carbonTracker.footprint.model.footprint;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class FootprintMapper implements ResultSetExtractor {

    public Object extractData(ResultSet rs) throws SQLException, DataAccessException{

        Map<Integer, Footprint> map = new HashMap<Integer, Footprint>();

        Footprint footprint;

        while(rs.next()){
            int id = rs.getInt("id");

            footprint = map.get(id);

            if(footprint == null){
                footprint = new Footprint();

                User user =  new User();
                user.setId(rs.getInt("id"));
                user.setFirstName(rs.getNString("first_name"));
                user.setLastName(rs.getNString("last_name"));
                user.setEmail(rs.getNString("email"));
                user.setPassword(rs.getNString("password"));
                user.setFootPrint(rs.getDouble("footPrint"));

                footprint.setUser(user);
                map.put(id, footprint);;


                List vehicleList = footprint.getVehicles();
                if(vehicleList == null ){

                    vehicleList = new ArrayList<Vehicle>();

                    Vehicle vehicle = new Vehicle();
                            vehicle.setId(rs.getInt("v.id"));
                            vehicle.setType(rs.getString("v.type"));
                            vehicle.setMpg(rs.getString("v.mpg"));
                            vehicle.setUserId(rs.getInt("v.userId"));

                    if(vehicle.getId() != 0) {
                        vehicleList.add(vehicle);
                        footprint.setVehicles(vehicleList);
                    }
                }

               List homeList = footprint.getHomes();
                if(homeList == null){
                    homeList = new ArrayList<Home>();

                    Home home = new Home();
                    home.setId(rs.getInt("h.id"));
                    home.setHomeType(rs.getNString("h.homeType"));
                    home.setHomeSize(rs.getInt("h.homeSize"));
                    home.setHomeGHG(rs.getDouble("h.homeGHG"));
                    home.setUserId(rs.getInt("h.userId"));

                    if(home.getId() != 0) {
                        homeList.add(home);
                        footprint.setHomes(homeList);
                    }
                }

                List offSettersList = footprint.getOffSetters();
                if(offSettersList == null){
                    offSettersList = new ArrayList();

                    OffSetters offSetters = new OffSetters();
                    offSetters.setId(rs.getInt("o.id"));
                    offSetters.setType(rs.getNString("o.type"));
                    offSetters.setProduct(rs.getNString("o.product"));
                    offSetters.setCCS(rs.getDouble("o.CCS"));
                    offSetters.setUserId(rs.getInt("userId"));

                    if(offSetters.getId() != 0) {
                        offSettersList.add(offSetters);
                        footprint.setOffSetters(offSettersList);
                    }

                }
            }

            if(footprint.getVehicles() != null){
                List<Vehicle> vehicles = footprint.getVehicles();

                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("v.id"));
                vehicle.setType(rs.getString("v.type"));
                vehicle.setMpg(rs.getString("v.mpg"));
                vehicle.setUserId(rs.getInt("v.userId"));

                boolean checkVehicleList = vehicles.stream().anyMatch(v -> v.getId() == vehicle.getId());
                if(!checkVehicleList) {
                    vehicles.add(vehicle);
                    footprint.setVehicles(vehicles);
                }

            }

            if(footprint.getHomes() != null){
                List<Home> homes = footprint.getHomes();

                Home home = new Home();
                        home.setId(rs.getInt("h.id"));
                        home.setHomeType(rs.getNString("h.homeType"));
                        home.setHomeSize(rs.getInt("h.homeSize"));
                        home.setUserId(rs.getInt("h.userId"));

                boolean checkHomeList = homes.stream().anyMatch(h -> h.getId() == home.getId());
                if(!checkHomeList) {
                    homes.add(home);
                    footprint.setHomes(homes);
                }

            }

            if(footprint.getOffSetters() != null){
                List<OffSetters> offSetters = footprint.getOffSetters();

                OffSetters offSetter = new OffSetters();
                offSetter.setId(rs.getInt("o.id"));
                offSetter.setType(rs.getNString("o.type"));
                offSetter.setProduct(rs.getNString("o.product"));
                offSetter.setCCS(rs.getDouble("o.CCS"));
                offSetter.setUserId(rs.getInt("userId"));


                boolean checkOffSetterList = offSetters.stream().anyMatch(o -> o.getId() == offSetter.getId());
                if(!checkOffSetterList){
                    offSetters.add(offSetter);
                    footprint.setOffSetters(offSetters);
                }
            }
        }

        return new ArrayList<>(map.values());
    }
}

