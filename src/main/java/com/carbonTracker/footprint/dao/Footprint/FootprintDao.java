package com.carbonTracker.footprint.dao.Footprint;

import com.carbonTracker.footprint.model.footprint.Footprint;

import java.util.List;
import java.util.Optional;

public interface FootprintDao {

    List<Footprint> getUserFootprint(int id);
    List<Footprint> userFootprint(String email);
}
