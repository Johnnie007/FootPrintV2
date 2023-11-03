package com.carbonTracker.footprint.dao.GHGStorage;

import com.carbonTracker.footprint.model.GHGStorage.GHGStorage;
import com.carbonTracker.footprint.model.GHGStorage.GHGStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class GHGStorageDaoImpl implements GHGStorageDao{

    public final JdbcTemplate jdbcTemplate;

    @Autowired
    public GHGStorageDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<GHGStorage> getStorage(int userId){
        String sql = """
                SELECT id, vehicleTotal, homeTotal, storageMonth, userId
                FROM GHGStorage
                WHERE userId = ?
                """;

        return jdbcTemplate.query(sql, new GHGStorageMapper(), userId);
    }

    @Override
    public int addStorage(GHGStorage ghgStorage, int userId){
        String sql = """
                INSERT into GHGStorage(vehicleTotal, homeTotal, storageMonth, userId)
                VALUES(?,?,?,?)
                """;
        return jdbcTemplate.update(sql, ghgStorage.getVehicleTotal(), ghgStorage.getHomeTotal(), ghgStorage.getStorageMonth(),userId);
    }

    @Override
    public int updateStorage(GHGStorage ghgStorage, int userId){
        String sql = """
                UPDATE GHGStorage
                SET vehicleTotal = ?, homeTotal = ?
                WHERE userId = ?
                """;
        return jdbcTemplate.update(sql, ghgStorage.getVehicleTotal(), ghgStorage.getHomeTotal());
    }
}
