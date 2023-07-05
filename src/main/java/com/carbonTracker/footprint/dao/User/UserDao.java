package com.carbonTracker.footprint.dao.User;

import com.carbonTracker.footprint.model.user.User;

import java.util.List;
import java.util.Optional;
public interface UserDao {

    List<User> findAllUsers();
    int addUser(User user);
    Optional<User> findUserById(int id);
    int deleteUser(int id);
    int updateUser(int id, User user);

}
