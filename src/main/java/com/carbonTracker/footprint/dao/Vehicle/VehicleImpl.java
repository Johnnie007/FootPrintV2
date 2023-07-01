package com.carbonTracker.footprint.dao.Vehicle;

import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import com.carbonTracker.footprint.model.vehicle.VehicleRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleImpl implements VehicleDao{

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public VehicleImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int addUserVehicle(Vehicle vehicle, int id){
        String sql = """
                INSERT into vehicle(make, model, vehicleYear, userId)
                VALUES(?,?,?, ?)
                """;
           return jdbcTemplate.update(sql, vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), id);
    }

    @Override
    public int updateUserVehicle(Vehicle vehicle, int userId){
        String sql = """
                UPDATE vehicle 
                SET make = ?, model = ?, vehicleYear = ?
                WHERE userId = ? AND id = ?
                """;
        return jdbcTemplate.update(sql, vehicle.getMake(), vehicle.getModel(), vehicle.getYear(), userId, vehicle.getId());
    }

    @Override
    public List<Vehicle> findVehicleByUserId(int id){
        String sql = """
                SELECT id, make, model, vehicleYear, userId
                FROM vehicle
                WHERE userId = ?
                """;
        return jdbcTemplate.query(sql, new VehicleRowMapper(), id);
    }

    @Override
    public int deleteVehicle(int id, Vehicle vehicle){
        String sql = """
                DELETE FROM vehicle
                WHERE userID = ? AND id = ?
                """;
        return jdbcTemplate.update(sql, id, vehicle.getId());
    }
}
