package com.carbonTracker.footprint.model.footprint;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.offsetters.Offsetters;
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

              User user = new User(
                        rs.getInt("u.id"),
                        rs.getNString("u.first_name"),
                        rs.getNString("u.last_name"),
                        rs.getNString("u.email"),
                        rs.getNString("u.password"),
                        rs.getNString("u.lifeStyle"),
                        rs.getInt("u.footPrint")
                );
                footprint.setUser(user);
                map.put(id, footprint);;


                List vehicleList = footprint.getVehicles();
                if(vehicleList == null ){

                    vehicleList = new ArrayList<Vehicle>();



                    Vehicle vehicle = new Vehicle(rs.getInt("v.id"),
                            rs.getString("v.type"),
                            rs.getString("v.mpg"),
                            rs.getInt("v.userId"));



                    vehicleList.add(vehicle);
                   footprint.setVehicles(vehicleList);
                }

               List homeList = footprint.getHomes();
                if(homeList == null){
                    homeList = new ArrayList<Home>();

                    Home home = new Home(rs.getInt("h.id"),
                            rs.getNString("h.homeType"),
                            rs.getInt("h.homeSize"),
                            rs.getInt("h.userId"));

                    homeList.add(home);

                    footprint.setHomes(homeList);
                }

                List offSettersList = footprint.getOffsetters();
                if(offSettersList == null){
                    offSettersList = new ArrayList();

                    Offsetters offsetters = new Offsetters(rs.getInt("o.id"),
                            rs.getNString("o.type"),
                            rs.getNString("o.product"),
                            rs.getInt("o.CCS")
                    );

                    offSettersList.add(offsetters);
                }
            }

            if(footprint.getVehicles() != null){
                List<Vehicle> vehicles = footprint.getVehicles();

                Vehicle vehicle = new Vehicle(rs.getInt("v.id"),
                        rs.getString("v.type"),
                        rs.getString("v.mpg"),
                        rs.getInt("v.userId"));

                boolean checkVehicleList = vehicles.stream().anyMatch(v -> v.getId() == vehicle.getId());
                if(!checkVehicleList) {
                    vehicles.add(vehicle);
                    footprint.setVehicles(vehicles);
                }

            }

            if(footprint.getHomes() != null){
                List<Home> homes = footprint.getHomes();

                Home home = new Home(rs.getInt("h.id"),
                        rs.getNString("h.homeType"),
                        rs.getInt("h.homeSize"),
                        rs.getInt("h.userId"));

                boolean checkHomeList = homes.stream().anyMatch(h -> h.getId() == home.getId());
                if(!checkHomeList) {
                    homes.add(home);
                    footprint.setHomes(homes);
                }

            }

            if(footprint.getOffsetters() != null){
                List<Offsetters> offsetters = footprint.getOffsetters();

                Offsetters offsetter = new Offsetters(
                        rs.getInt("o.id"),
                        rs.getNString("o.type"),
                        rs.getNString("o.product"),
                        rs.getInt("o.CCS")
                );

                boolean checkOffsetterList = offsetters.stream().anyMatch(o -> o.getId() == offsetter.getId());
                if(!checkOffsetterList){
                    offsetters.add(offsetter);
                    footprint.setOffsetters(offsetters);
                }
            }
        }

        return new ArrayList<>(map.values());
    }
}

