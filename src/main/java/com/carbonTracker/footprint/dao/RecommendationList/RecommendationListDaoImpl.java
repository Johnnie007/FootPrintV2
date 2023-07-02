package com.carbonTracker.footprint.dao.RecommendationList;

import com.carbonTracker.footprint.model.recommendationList.RecommendationList;
import com.carbonTracker.footprint.model.recommendationList.RecommendationListRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RecommendationListDaoImpl implements RecommendationListDao{

    JdbcTemplate jdbcTemplate;

    @Override
    public List<RecommendationList> getRecommendation() {
        String sql = """
                SELECT *
                FROM recommendationList
                """;
        return jdbcTemplate.query(sql, new RecommendationListRowMapper());
    }
}
