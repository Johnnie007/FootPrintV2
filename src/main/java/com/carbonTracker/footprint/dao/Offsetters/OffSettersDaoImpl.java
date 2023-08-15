package com.carbonTracker.footprint.dao.Offsetters;

import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.offSetters.OffSettersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OffSettersDaoImpl implements OffSettersDao {

    JdbcTemplate jdbcTemplate;

    @Autowired OffSettersDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<OffSetters> getOffSetters(int id){
        String sql = """
                SELECT *
                FROM offSetters
                WHERE userId = ?
                """;

        return jdbcTemplate.query(sql, new OffSettersMapper(), id);
    }

    @Override
    public int addOffSetter(OffSetters offsetters, int id){
        String sql = """
                INSERT into offSetters( type, product, CCS, userId)
                VALUES (?, ?, ?, ?)
                """;
        return jdbcTemplate.update(sql, offsetters.getType(), offsetters.getProduct(), offsetters.getCCS(), id);
    }

    @Override
    public int deleteOffSetter(int userId, OffSetters offSetters){
        String sql = """
                DELETE FROM OffSetters
                WHERE userId = ? and id = ?
                """;

        return jdbcTemplate.update(sql, userId, offSetters.getId());
    }
}
