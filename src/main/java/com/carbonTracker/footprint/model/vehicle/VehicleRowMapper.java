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
                rs.getString("type"),
                rs.getDouble("mpg"),
                rs.getInt("userId")
        );
    }
}
