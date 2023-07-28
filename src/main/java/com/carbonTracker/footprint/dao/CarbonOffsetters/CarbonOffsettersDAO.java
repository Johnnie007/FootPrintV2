package com.carbonTracker.footprint.dao.CarbonOffsetters;

import com.carbonTracker.footprint.model.carbonOffsetters.CarbonOffsetters;

import java.util.List;

public interface CarbonOffsettersDAO {

    List<CarbonOffsetters> getOffsetters(int id);
    int addOffsetter(CarbonOffsetters offsetters);

}
