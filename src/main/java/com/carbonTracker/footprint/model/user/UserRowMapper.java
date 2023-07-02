package com.carbonTracker.footprint.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new User(
                rs.getInt("id"),
                rs.getNString("first_name"),
                rs.getNString("last_name"),
                rs.getNString("email"),
                rs.getNString("lifeStyle"),
                rs.getInt("footPrint")
                );
    }
}
