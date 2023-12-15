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

    public int createUser(String firstName, String lastName, String email, String password,String month_joined){
        String sql = """
                INSERT into user(first_name, last_name, email, password, month_joined)
                VALUES (?,?,?,?,?)
                """;

        return jdbcTemplate.update(sql, firstName, lastName, email, password, month_joined);
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
    public Map<String, Object> findUser(String email){
        String sql = """
                SELECT id, month_joined, first_name, last_name, footprint
                FROM user
                WHERE email = ?;
                """;
        List<Map<String, Object>> test = jdbcTemplate.queryForList(sql, new Object[] {email});

        return test.stream().findFirst().get();
    }

    @Override
    public Optional<Map<String, Object>> getEmail(String email){
        String sql = """
                SELECT id, email, password
                FROM user
                WHERE email = ?
                """;
         List<Map<String, Object>> test = jdbcTemplate.queryForList(sql, new Object[] {email});

        return test.stream().findFirst();
    }

    @Override
    public int updateUser(int id, double footprint ){
        String sql = """
                UPDATE user
                SET footprint = ?
                WHERE id = ?;
                """;

        return jdbcTemplate.update(sql, footprint,id);
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
                SELECT id, email, password
                From user
                WHERE email = ?
                """;
        List<Map<String, Object>> getUser = jdbcTemplate.queryForList(sql, new Object[] {email});
        List<User> user = getUser.stream().map(u ->{
            User userAuth = new User();
            userAuth.setEmail(String.valueOf(u.get("email")));
            userAuth.setPassword(String.valueOf(u.get("password")));
            return userAuth;
        }).collect(Collectors.toList());
        if(user.isEmpty()){
            return Optional.empty();
        }else{
            return Optional.of(user.stream().findFirst().get());
        }

    }

}
