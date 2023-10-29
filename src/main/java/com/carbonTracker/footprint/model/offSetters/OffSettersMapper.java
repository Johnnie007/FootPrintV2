package com.carbonTracker.footprint.model.offSetters;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OffSettersMapper implements RowMapper<OffSetters> {

    @Override
    public OffSetters mapRow(ResultSet rs, int rowNum) throws SQLException {
        OffSetters offSetters = new OffSetters();
                offSetters.setId(rs.getInt("id"));
                offSetters.setMonth_added(rs.getNString("month_added"));
                offSetters.setType(rs.getNString("type"));
                offSetters.setProduct(rs.getNString("product"));
                offSetters.setCCS(rs.getInt("CCS"));
                offSetters.setUserId(rs.getInt("userId"));

                return offSetters;
    }
}