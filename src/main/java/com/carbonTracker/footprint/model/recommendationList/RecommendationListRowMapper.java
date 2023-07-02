package com.carbonTracker.footprint.model.recommendationList;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecommendationListRowMapper implements RowMapper<RecommendationList> {

    @Override
    public RecommendationList mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new RecommendationList(
                rs.getInt("id"),
                rs.getNString("type"),
                rs.getNString("product"),
                rs.getNString("productLocation")
        );
    }
}
