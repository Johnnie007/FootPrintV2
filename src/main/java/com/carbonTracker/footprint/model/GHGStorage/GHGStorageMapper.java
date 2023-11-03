package com.carbonTracker.footprint.model.GHGStorage;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class GHGStorageMapper implements RowMapper<GHGStorage>{


    @Override
    public GHGStorage mapRow(ResultSet rs, int rowNum) throws SQLException {
        GHGStorage ghgStorage = new GHGStorage();
        ghgStorage.setId(rs.getInt("id"));
        ghgStorage.setVehicleTotal(rs.getDouble("vehicleTotal"));
        ghgStorage.setHomeTotal(rs.getDouble("homeTotal"));
        ghgStorage.setStorageMonth(rs.getNString("storageMonth"));

        return ghgStorage;
    }
}
