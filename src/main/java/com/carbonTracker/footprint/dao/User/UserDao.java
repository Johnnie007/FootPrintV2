package com.carbonTracker.footprint.dao.User;

import com.carbonTracker.footprint.model.user.User;

import java.util.Map;
import java.util.Optional;
public interface UserDao {

  Optional<User> findUserById(int id);
    Map<String, Object> findUser(String Email);
    int deleteUser(int id);
    int updateUser(int id, User user);

    Optional<User> findUserEmail(String email);

    int createUser(String firstName, String lastName, String email, String password, String month_joined);

    Optional<Map<String, Object>> getEmail(String email);


}
