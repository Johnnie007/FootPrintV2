package com.carbonTracker.footprint.dao.Footprint;

import org.springframework.jdbc.core.JdbcTemplate;

public class FootPrintDaoImpl implements  FootPrintDao{

    JdbcTemplate jdbcTemplate;


    @Override
    public int getFootPrint(int id) {
        String sql = """
                SELECT
                """;
    }
}
