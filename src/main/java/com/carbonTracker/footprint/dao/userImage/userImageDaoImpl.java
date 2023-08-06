package com.carbonTracker.footprint.dao.userImage;

import com.carbonTracker.footprint.model.userImage.UserImage;
import com.carbonTracker.footprint.model.userImage.UserImageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserImageDaoImpl implements UserImageDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserImageDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUserImage(UserImage userImage, int id){
        String sql = """
                INSERT into profileImage (type, imageName, imageData, userId)
                Values (?,?,?,?)
                """;
        return jdbcTemplate.update(sql, userImage.getType(), userImage.getImageName(), userImage.getImageData(), id);
    }

    @Override
    public int updateUserImage(int id, UserImage userImage){
        String sql = """
                Update profileImage
                SET type = ?, imageName = ?, imageData = ?
                WHERE userId = ?
                """;
        return jdbcTemplate.update(sql, userImage.getType(), userImage.getImageName(), userImage.getImageName(), id);
    }

    @Override
    public Optional<UserImage> findUserImage(int id){
        String sql = """
                SELECT *
                FROM profileImage
                WHERE userId =  ?
                """;
        return jdbcTemplate.query(sql, new UserImageMapper(), id).stream().findFirst();
    }
}
