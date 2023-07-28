package com.carbonTracker.footprint.dao.Offsetters;

import com.carbonTracker.footprint.model.offsetters.Offsetters;
import com.carbonTracker.footprint.model.offsetters.OffsettersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class OffsettersDAOImpl implements OffsettersDAO {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public List<Offsetters> getOffsetters(int id){
        String sql = """
                SELECT *
                FROM offsetters
                Where userid = ?
                """;

        return jdbcTemplate.query(sql, new OffsettersMapper(), id);
    }

    @Autowired
    public int addOffsetter(Offsetters offsetters){
        String sql = """
                INSERT into carbonOffsetter( type, product, CCS)
                VALUES (?, ?, ?)
                """;
        return jdbcTemplate.update(sql, offsetters.getType(), offsetters.getProduct(), offsetters.getCCS());
    }
}
