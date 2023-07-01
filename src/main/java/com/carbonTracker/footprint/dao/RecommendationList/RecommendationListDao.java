package com.carbonTracker.footprint.dao.RecommendationList;

import com.carbonTracker.footprint.model.recommendationList.RecommendationList;

import java.util.List;

public interface RecommendationListDao {

    List<RecommendationList> getRecommendation(int userId);
}
