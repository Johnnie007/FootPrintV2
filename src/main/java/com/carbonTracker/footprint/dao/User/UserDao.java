package com.carbonTracker.footprint.dao.User;

import com.carbonTracker.footprint.model.user.User;

import java.util.List;
import java.util.Map;
import java.util.Optional;
public interface UserDao {

    List<Map<String, Object>> findAllUsers();
    int addUser(User user);
    Optional<User> findUserById(int id);
    int deleteUser(int id);
    int updateUser(int id, User user);

    Optional<User> findUserEmail(String email);

    int createUser(String firstName, String lastName, String email, String password);

    Optional<Map<String, Object>> getEmail(String email);


}
