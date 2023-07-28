package com.carbonTracker.footprint.model.offSetters;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OffSettersMapper implements RowMapper<OffSetters> {

    @Override
    public OffSetters mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new OffSetters(
                rs.getInt("id"),
                rs.getNString("type"),
                rs.getNString("product"),
                rs.getInt("CCS"),
                rs.getInt("userId")
                );
    }
}