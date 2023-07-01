package com.carbonTracker.footprint.model.footprint;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FootPrintRowMapper implements RowMapper<FootPrint> {

    @Override
    public FootPrint mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new FootPrint(
                rs.getInt("id")
//                rs.getObject("user"),
//                rs.getObject("vehicle"),
//                rs.getObject("home"),
//                rs.getObject("recommendationList")
                );
    }
}
