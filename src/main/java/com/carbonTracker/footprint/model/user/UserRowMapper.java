package com.carbonTracker.footprint.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user =  new User();
                user.setId(rs.getInt("id"));
                user.setMonth_joined("month_joined");
                user.setFirstName(rs.getNString("first_name"));
                user.setLastName(rs.getNString("last_name"));
                user.setEmail(rs.getNString("email"));
                user.setPassword(rs.getNString("password"));
                user.setLifeStyle(rs.getNString("lifeStyle"));
                user.setFootPrint(rs.getInt("footprint"));
        return user;
    }
}
