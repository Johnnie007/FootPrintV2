package com.carbonTracker.footprint.model.recommendationList;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RecommendationListRowMapper implements RowMapper<RecommendationList> {

    @Override
    public RecommendationList mapRow(ResultSet rs, int rowNum) throws SQLException{

        RecommendationList recommendationList = new RecommendationList();
                recommendationList.setId(rs.getInt("id"));
                recommendationList.setType(rs.getNString("type"));
                recommendationList.setProduct(rs.getNString("product"));
                recommendationList.setProductLocation(rs.getNString("productLocation"));
                recommendationList.setCCS(rs.getDouble("CCS"));
                return recommendationList;
    }
}
