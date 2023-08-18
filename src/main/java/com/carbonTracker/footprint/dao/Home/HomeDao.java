package com.carbonTracker.footprint.dao.Home;

import com.carbonTracker.footprint.model.home.Home;

import java.util.List;

public interface HomeDao {

    List<Home> getUserHomes(int userId);
    int addHome(Home home, int userId);
    int deleteHome(Home home, int userId);

    int updateHome(Home home, int userId);

}
