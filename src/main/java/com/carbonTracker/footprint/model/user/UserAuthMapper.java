package com.carbonTracker.footprint.model.user;


import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component("UserAuthMapper")
public class UserAuthMapper implements UserMapperInterface{

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException{
        User user =  new User();
        user.setEmail(rs.getNString("email"));
        user.setPassword(rs.getNString("password"));
        return user;
    }
}
