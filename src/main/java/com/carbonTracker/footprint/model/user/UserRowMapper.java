package com.carbonTracker.footprint.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

//    @Override
//    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
//        User user =  new User(
//        rs.getInt("id"),
//        rs.getNString("first_name"),
//        rs.getNString("last_name"),
//        rs.getNString("email"),
//        rs.getNString("password"),
//        rs.getNString("lifeStyle"),
//        rs.getInt("footPrint"));
//        return user;
//    }

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user =  new User();
               user.setId(rs.getInt("id"));
                user.setFirstName(rs.getNString("first_name"));
                user.setLastName(rs.getNString("last_name"));
                user.setEmail(rs.getNString("email"));
                user.setPassword(rs.getNString("password"));
                user.setLifeStyle(rs.getNString("lifeStyle"));
                user.setFootPrint(rs.getInt("footPrint"));
       System.out.println(rs.getNString("email"));
        System.out.println(user.getEmail());
        return user;
    }
}
