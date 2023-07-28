package com.carbonTracker.footprint.dao.Offsetters;

import com.carbonTracker.footprint.model.offsetters.Offsetters;

import java.util.List;

public interface OffsettersDAO {

    List<Offsetters> getOffsetters(int id);
    int addOffsetter(Offsetters offsetters);

}
