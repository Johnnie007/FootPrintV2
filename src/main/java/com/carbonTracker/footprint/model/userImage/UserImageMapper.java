package com.carbonTracker.footprint.model.userImage;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImageMapper implements RowMapper<UserImage> {

    @Override
    public UserImage mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new UserImage(
                rs.getInt("id"),
                rs.getNString("type"),
                rs.getNString("imageName"),
                rs.getBytes("imageData"),
                rs.getInt("userId")
        );
    }
}
