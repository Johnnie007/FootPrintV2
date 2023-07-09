package com.carbonTracker.footprint.model.footprint;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FootprintMapper implements ResultSetExtractor {

    public Object extractData(ResultSet rs) throws SQLException, DataAccessException{
        Map<Integer, Footprint> map = new HashMap<Integer, Footprint>();

       // User user = null;
       // Vehicle vehicle = null;
       // Home home = null;
      //  List homeList = null;
      //  List vehicleList = null;




        while(rs.next()){

            int id = rs.getInt("id");
            //System.out.println(rs.getInt("h.id"));

            Footprint footprint = map.get(id);
            if(footprint == null){
                footprint = new Footprint();

              User user = new User(
                        rs.getInt(id),
                        rs.getNString("u.first_name"),
                        rs.getNString("u.last_name"),
                        rs.getNString("u.email"),
                        rs.getNString("u.lifeStyle"),
                        rs.getInt("u.footPrint")
                );
                footprint.setUser(user);
                map.put(id, footprint);

               List vehicleList = footprint.getVehicles();
                if(vehicleList == null){

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

                    System.out.println(home);

                    homeList.add(home);

                    footprint.setHomes(homeList);
                }
            }

        }

        return new ArrayList<Footprint>(map.values());
    }
}
