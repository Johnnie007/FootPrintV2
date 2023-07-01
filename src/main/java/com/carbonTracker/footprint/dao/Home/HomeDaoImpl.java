package com.carbonTracker.footprint.dao.Home;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.home.HomeRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class HomeDaoImpl implements HomeDao{

    JdbcTemplate jdbcTemplate;

    @Override
    public List<Home> getUserHomes(int userId){
        String sql = """
                SELECT id, type, homeSize
                FROM home
                WHERE userId = ?
                """;
        return jdbcTemplate.query(sql, new HomeRowMapper(), userId);
    }

    @Override
    public int addHome(Home home){
        String sql = """
                INSERT homeType, homeSize, userId
                VALUES(?,?,?,?)
                """;
        return jdbcTemplate.update(sql, home.getHomeType(), home.getHomeSize(), home.getUserId());
    }

    @Override
    public int deleteHome(Home home){
        String sql = """
                DELETE FROM home
                WHERE userId = ? and id = ?
                """;
        return jdbcTemplate.update(sql, home.getUserId(), home.getId());
    }

}
