package com.carbonTracker.footprint.model.vehicle;

import com.carbonTracker.footprint.model.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements RowMapper <Vehicle> {

    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getInt("id"));
                vehicle.setType(rs.getString("type"));
                vehicle.setMpg(rs.getString("mpg"));
                vehicle.setVehicleGHG(rs.getInt("vehicleGHG"));
                vehicle.setUserId(rs.getInt("userId"));
        return vehicle;
    }
}
