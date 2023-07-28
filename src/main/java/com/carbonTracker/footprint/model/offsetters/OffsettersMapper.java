package com.carbonTracker.footprint.model.offsetters;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OffsettersMapper implements RowMapper<Offsetters> {

    @Override
    public Offsetters mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Offsetters(
                rs.getInt("id"),
                rs.getNString("type"),
                rs.getNString("product"),
                rs.getInt("CCS")
                );
    }
}