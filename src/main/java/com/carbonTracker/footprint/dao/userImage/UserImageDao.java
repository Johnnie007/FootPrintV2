package com.carbonTracker.footprint.dao.userImage;

import com.carbonTracker.footprint.model.userImage.UserImage;

import java.util.Optional;

public interface UserImageDao {

    Optional<UserImage> findUserImage(int id);
    int addUserImage(UserImage userImage, int id);
    int updateUserImage(int id, UserImage userImage);
}
