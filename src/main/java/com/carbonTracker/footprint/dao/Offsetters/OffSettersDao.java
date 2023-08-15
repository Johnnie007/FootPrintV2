package com.carbonTracker.footprint.dao.Offsetters;

import com.carbonTracker.footprint.model.offSetters.OffSetters;

import java.util.List;

public interface OffSettersDao {

    List<OffSetters> getOffSetters(int id);
    int addOffSetter(OffSetters offsetters, int id);

    int deleteOffSetter(int userId, OffSetters offSetters);

}
