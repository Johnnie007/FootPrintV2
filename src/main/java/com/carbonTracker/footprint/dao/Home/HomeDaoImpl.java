package com.carbonTracker.footprint.dao.Home;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.home.HomeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class HomeDaoImpl implements HomeDao{

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public HomeDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Home> getUserHomes(int userId){
        String sql = """
                SELECT id, homeType, homeGHG,  homeSize, userId
                FROM home
                WHERE userId = ?
                """;
        return jdbcTemplate.query(sql, new HomeRowMapper(), userId);
    }

    @Override
    public int addHome(Home home, int userId){
        String sql = """
                INSERT into home(homeType, homeGHG, homeSize, userId)
                VALUES(?,?,?,?)
                """;
        return jdbcTemplate.update(sql, home.getHomeType(), home.getHomeGHG(), home.getHomeSize(), userId);
    }

    @Override
    public int deleteHome(Home home, int userId){
        String sql = """
                DELETE FROM home
                WHERE userId = ? and id = ?
                """;
        return jdbcTemplate.update(sql, home.getUserId(), home.getId());
    }

    @Override
    public int updateHome(Home home, int userId){
        String sql = """
                UPDATE home
                SET homeType = ?, homeSize = ?
                WHERE userId = ?
                """;
        return jdbcTemplate.update(sql, home.getHomeType(), home.getHomeSize(), userId);
    }

}
