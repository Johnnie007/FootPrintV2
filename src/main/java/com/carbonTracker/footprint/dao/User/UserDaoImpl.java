package com.carbonTracker.footprint.dao.User;

import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.user.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
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
                INSERT into user(first_name, last_name, email, footPrint, lifeStyle)
                VALUES (?,?,?,?,?)
                """;
        return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getFootPrint(), user.getLifeStyle());
    }

    @Override
    public List<User> findAllUsers() {
        String sql = """
                SELECT *
                FROM user
                LIMIT 100;
                """;
        return jdbcTemplate.query(sql, new UserRowMapper());
    }

    @Override
    public Optional <User> findUserById(int id){
        String sql = """
                SELECT id, first_name, last_name, email, footPrint, lifeStyle
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

//    @Override
//    public List <User> getUserFootPrint(int id){
//        String sql = """
//                 SElECT * FROM user u
//                    INNER JOIN vehicle v
//                    ON u.id = v.userId
//                    INNER JOIN home h
//                    ON h.userID = u.id
//                    WHERE u.id = ?;
//                """;
//        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<User>(User.class), id );
//    }
}
