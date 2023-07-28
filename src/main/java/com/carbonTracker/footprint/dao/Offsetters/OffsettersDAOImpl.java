package com.carbonTracker.footprint.dao.Offsetters;

import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.offSetters.OffSettersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OffsettersDAOImpl implements OffsettersDAO {

    JdbcTemplate jdbcTemplate;

    @Override
    public List<OffSetters> getOffsetters(int id){
        String sql = """
                SELECT *
                FROM offsetters
                Where userid = ?
                """;

        return jdbcTemplate.query(sql, new OffSettersMapper(), id);
    }

    @Override
    public int addOffsetter(OffSetters offsetters){
        String sql = """
                INSERT into carbonOffsetter( type, product, CCS)
                VALUES (?, ?, ?)
                """;
        return jdbcTemplate.update(sql, offsetters.getType(), offsetters.getProduct(), offsetters.getCCS());
    }
}
