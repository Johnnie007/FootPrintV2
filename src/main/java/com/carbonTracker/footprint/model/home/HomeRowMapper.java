package com.carbonTracker.footprint.model.home;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeRowMapper implements RowMapper<Home>{

    @Override
    public Home mapRow(ResultSet rs, int rowNum) throws SQLException {
        Home home = new Home();
        home.setId(rs.getInt("id"));
        home.setMonth("month_added");
        home.setHomeType(rs.getNString("homeType"));
        home.setHomeGHG(rs.getInt("homeGHG"));
        home.setHomeSize(rs.getInt("homeSize"));
        home.setUserId(rs.getInt("userId"));
        return home;
    }
}
