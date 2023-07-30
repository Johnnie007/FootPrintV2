package com.carbonTracker.footprint.dao.User;

import com.carbonTracker.footprint.model.user.User;

import com.carbonTracker.footprint.model.user.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class UserDaoImpl implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUser(User user){
        String sql = """
                INSERT into user(first_name, last_name, email, footPrint, lifeStyle)
                VALUES (?,?,?,?,?)
                """;
        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getFootPrint(), user.getLifeStyle());
    }

    public int createUser(String firstName, String lastName, String email, String password){
        String sql = """
                INSERT into user(first_name, last_name, email, password)
                VALUES (?,?,?,?)
                """;

        return jdbcTemplate.update(sql, firstName, lastName, email, password);
    }

    @Override
    public List<Map<String, Object>> findAllUsers() {
        String sql = """
                SELECT first_name, last_name, footprint, lifeStyle
                FROM user
                LIMIT 100;
                """;
        List<Map<String, Object>> listOfUser = jdbcTemplate.queryForList(sql, new Object[] {});
        return listOfUser;
    }

    @Override
    public Optional <User> findUserById(int id){
        String sql = """
                SELECT *
                FROM user
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public Optional<Map<String, Object>> getEmail(String email){
        String sql = """
                SELECT email, password
                FROM user
                WHERE email = ?
                """;
         List<Map<String, Object>> test = jdbcTemplate.queryForList(sql, new Object[] {email});

        return test.stream().findFirst();
    }

    @Override
    public int updateUser(int id, User user ){
        String sql = """
                UPDATE user
                SET first_name = ?, last_name = ?, email = ?, footPrint = ?, lifeStyle = ?
                WHERE id = ?;
                """;

        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getFootPrint(), user.getLifeStyle(),id);
    }

    @Override
    public int deleteUser(int id){
        String sql = """
                DELETE FROM user
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }

    @Override
    public Optional<User> findUserEmail(String email){
        String sql = """
                SELECT email, password
                From user
                WHERE email = ?
                """;
        List<Map<String, Object>> test = jdbcTemplate.queryForList(sql, new Object[] {email});

        List<User> user = test.stream().map(u ->{
            User userAuth = new User();
            userAuth.setEmail(String.valueOf(u.get("email")));
            userAuth.setPassword(String.valueOf(u.get("password")));
            return userAuth;
        }).collect(Collectors.toList());

        return Optional.of(user.stream().findFirst().get());
    }

}
