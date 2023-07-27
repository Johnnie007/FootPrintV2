package com.carbonTracker.footprint.model.carbonOffsetters;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CarbonOffsettersMapper implements RowMapper<CarbonOffsetters> {

    @Override
    public CarbonOffsetters mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CarbonOffsetters(
                rs.getInt("id"),
                rs.getNString("type"),
                rs.getNString("product"),
                rs.getInt("CCS")
                );
    }
}