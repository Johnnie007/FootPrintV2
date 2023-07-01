package com.carbonTracker.footprint.model.home;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HomeRowMapper implements RowMapper<Home>{

    @Override
    public Home mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Home(
                rs.getInt("id"),
                rs.getNString("homeType"),
                rs.getInt("homeSize"),
                rs.getInt("userId")
        );
    }
}
