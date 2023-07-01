package com.carbonTracker.footprint.controller;
import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.dao.Vehicle.VehicleDao;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/footprint")
public class FootprintController {

    private final UserDao userDao;
    private final VehicleDao vehicleDao;

    @Autowired
    public FootprintController(UserDao userDao, VehicleDao vehicleDao){
        this.userDao = userDao;
        this.vehicleDao = vehicleDao;
    }

    @PostMapping("/add")
    public void addUser(@Valid @RequestBody User user){
        userDao.addUser(user);
    }

    @GetMapping("/all")
    public List<User> findAll(){
        return userDao.findAllUsers();
    }

    @GetMapping("{id}")
    public  User findById(@PathVariable("id") int id){
        return userDao.findUserById(id).orElseThrow(() -> new RuntimeException("user not found"));
    }

    @PutMapping("/update/{id}")
    public int updateUser(@Valid @RequestBody User user,@PathVariable("id") int id){
        return userDao.updateUser(id,user);
    }

    @PostMapping("{id}/add/vehicle")
    public void addVehicleByUserId(@Valid @RequestBody Vehicle vehicle, @PathVariable("id") int id){
        vehicleDao.addUserVehicle(vehicle, id);
    }

    @GetMapping("{id}/vehicle")
    public ResponseEntity<List<Vehicle>> getVehicleByUserId(@PathVariable("id") int id){
        return ResponseEntity.ok(vehicleDao.findVehicleByUserId(id));
    }

    @PutMapping("{id}/update/vehicle")
    public int updateUserVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable int id){
        return vehicleDao.updateUserVehicle(vehicle, id);
    }

    @DeleteMapping("{id}/delete/vehicle")
    public int deleteVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable int id){
        return vehicleDao.deleteVehicle(id, vehicle);
    }

}
