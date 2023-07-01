package com.carbonTracker.footprint.dao.Vehicle;

import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleDao {

    int addUserVehicle(Vehicle vehicle, int id);
    int updateUserVehicle(Vehicle vehicle, int userId);
    List<Vehicle> findVehicleByUserId(int id);
    int deleteVehicle(int id, Vehicle vehicle);

}
