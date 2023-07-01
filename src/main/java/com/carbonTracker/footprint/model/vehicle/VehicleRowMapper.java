package com.carbonTracker.footprint.model.vehicle;

import com.carbonTracker.footprint.model.user.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRowMapper implements RowMapper <Vehicle> {

    @Override
    public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Vehicle(
                rs.getInt("id"),
                rs.getString("make"),
                rs.getString("model"),
                rs.getInt("vehicleYear"),
                rs.getInt("userId")
        );
    }
}
