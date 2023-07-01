package com.carbonTracker.footprint.dao.User;

import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.user.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
                INSERT into user(first_name, last_name, email, diet, lifeStyle)
                VALUES (?,?,?,?,?)
                """;
        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getDiet(), user.getLifeStyle());
    }

    @Override
    public List<User> findAllUsers() {
        String sql = """
                SELECT id, first_name, last_name, email, diet, lifeStyle
                FROM user
                LIMIT 100;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional <User> findUserById(int id){
        String sql = """
                SELECT id, first_name, last_name, email, diet, lifeStyle
                FROM user
                WHERE id = ?;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper(), id)
                .stream()
                .findFirst();
    }

    @Override
    public int updateUser(int id, User user ){
        String sql = """
                UPDATE user
                SET first_name = ?, last_name = ?, email = ?, diet = ?, lifeStyle = ?
                WHERE id = ?;
                """;

        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getDiet(), user.getLifeStyle(),id);
    }

    @Override
    public int deleteUser(int id){
        String sql = """
                DELETE FROM user
                WHERE id = ?;
                """;
        return jdbcTemplate.update(sql, id);
    }
}
