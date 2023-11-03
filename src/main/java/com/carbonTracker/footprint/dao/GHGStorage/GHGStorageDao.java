package com.carbonTracker.footprint.dao.GHGStorage;

import com.carbonTracker.footprint.model.GHGStorage.GHGStorage;

import java.util.List;

public interface GHGStorageDao {

    List<GHGStorage> getStorage(int userId);
    int addStorage(GHGStorage ghgStorage, int userId);
    int updateStorage(GHGStorage ghgStorage, int userId);
}
