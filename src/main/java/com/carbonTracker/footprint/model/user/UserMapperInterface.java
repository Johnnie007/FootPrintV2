package com.carbonTracker.footprint.model.user;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public interface UserMapperInterface extends RowMapper<User> {

//    default List<String> getColumnList(){
//        return Arrays.asList("id", "firstName", "last_name","email", "password", "footprint", "lifeStyle");
//    }
//
//    default  String getColumnListAsString(){
//        return getColumnList().stream().collect(Collectors.joining(", "));
//    }

    @Override
    default User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user =  new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getNString("first_name"));
                user.setLastName(rs.getNString("last_name"));
                user.setEmail(rs.getNString("email"));
                user.setPassword(rs.getNString("password"));
                user.setLifeStyle(rs.getNString("lifeStyle"));
                user.setFootPrint(rs.getInt("footPrint"));
         return user;
    }


}
