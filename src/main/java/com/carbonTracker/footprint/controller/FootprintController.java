package com.carbonTracker.footprint.controller;
import com.carbonTracker.footprint.dao.Footprint.FootprintDao;
import com.carbonTracker.footprint.dao.Home.HomeDao;
import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.dao.Vehicle.VehicleDao;
import com.carbonTracker.footprint.model.footprint.Footprint;
import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/footprint")
public class FootprintController {

    private final UserDao userDao;
    private final VehicleDao vehicleDao;
    private final HomeDao homeDao;
    private final FootprintDao footprintDao;

    //User Endpoints
    @Autowired
    public FootprintController(UserDao userDao, VehicleDao vehicleDao, HomeDao homeDao, FootprintDao footprintDao){
        this.userDao = userDao;
        this.vehicleDao = vehicleDao;
        this.homeDao = homeDao;
        this.footprintDao = footprintDao;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@Valid @RequestBody User user, UriComponentsBuilder ucb){
        int savedUser = userDao.addUser(user);
        URI locationOfUser = ucb
                .path("footprint/add")
                .buildAndExpand(savedUser)
                .toUri();
        System.out.println(ResponseEntity.created(locationOfUser).build());
        return ResponseEntity.created(locationOfUser).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(userDao.findAllUsers());
    }

    @GetMapping("{id}")
    public ResponseEntity<User> findById(@PathVariable("id") int id){
        Optional<User> user = userDao.findUserById(id);
        if(user.isPresent()){
            return ResponseEntity.ok(userDao.findUserById(id).orElseThrow(() -> new RuntimeException("user not found")));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update/{id}")
    public int updateUser(@Valid @RequestBody User user,@PathVariable("id") int id){
        return userDao.updateUser(id,user);
    }

    @GetMapping("/footprint/{id}")
    public Optional<Footprint> getUserFootPrint(@PathVariable("id") int id){
        return footprintDao.getUserFootprint(id).stream().findFirst();
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

    //Home Endpoints
    @GetMapping("{id}/home")
    public List<Home> getUserHomes(@PathVariable("id") int userId){
        return homeDao.getUserHomes(userId);
    }

    @PostMapping("{id}/home")
    public int addHome(@PathVariable("id") int userId, @Valid @RequestBody Home home ){
        return homeDao.addHome(home, userId);
    }

    @DeleteMapping("{id}/delete/home")
    public int deleteHome(@Valid @RequestBody Home home, @PathVariable("id") int userId){
        return homeDao.deleteHome(home, userId);
    }



}
