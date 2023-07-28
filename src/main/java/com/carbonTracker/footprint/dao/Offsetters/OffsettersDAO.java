package com.carbonTracker.footprint.dao.Offsetters;

import com.carbonTracker.footprint.model.offSetters.OffSetters;

import java.util.List;

public interface OffsettersDAO {

    List<OffSetters> getOffsetters(int id);
    int addOffsetter(OffSetters offsetters);

}
