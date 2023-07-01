package com.carbonTracker.footprint.dao.RecommendationList;

import com.carbonTracker.footprint.model.recommendationList.RecommendationList;
import com.carbonTracker.footprint.model.recommendationList.RecommendationListRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class RecommendationListDaoImpl implements RecommendationListDao{

    JdbcTemplate jdbcTemplate;

    @Override
    public List<RecommendationList> getRecommendation(int userId) {
        String sql = """
                SELECT transportation, plants, energy, goods
                FROM Recommemdations
                WHERE userId = ?
                """;
        return jdbcTemplate.query(sql, new RecommendationListRowMapper(), userId);
    }
}
