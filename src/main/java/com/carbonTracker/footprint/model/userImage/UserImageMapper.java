package com.carbonTracker.footprint.model.userImage;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserImageMapper implements RowMapper<UserImage> {

    @Override
    public UserImage mapRow(ResultSet rs, int rowNum) throws SQLException{
        UserImage userImage = new UserImage();

                userImage.setId(rs.getInt("id"));
                userImage.setType(rs.getNString("type"));
                userImage.setImageName(rs.getNString("imageName"));
                userImage.setImageData(rs.getBytes("imageData"));
                userImage.setId(rs.getInt("userId"));

                return userImage;
    }
}
