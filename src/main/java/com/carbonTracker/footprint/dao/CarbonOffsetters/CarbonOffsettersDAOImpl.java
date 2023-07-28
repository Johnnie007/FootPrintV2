package com.carbonTracker.footprint.dao.CarbonOffsetters;

import com.carbonTracker.footprint.model.carbonOffsetters.CarbonOffsetters;
import com.carbonTracker.footprint.model.carbonOffsetters.CarbonOffsettersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CarbonOffsettersDAOImpl implements CarbonOffsettersDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public List<CarbonOffsetters> getOffsetters(int id){
        String sql = """
                SELECT *
                FROM carbonOffsetters
                Where userid = ?
                """;

        return jdbcTemplate.query(sql, new CarbonOffsettersMapper(), id);
    }

    @Autowired
    public int addOffsetter(CarbonOffsetters offsetters){
        String sql = """
                INSERT into carbonOffsetter( type, product, CCS)
                VALUES (?, ?, ?)
                """;
        return jdbcTemplate.update(sql, offsetters.getType(), offsetters.getProduct(), offsetters.getCCS());
    }
}
