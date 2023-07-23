package com.carbonTracker.footprint.controller;
import com.carbonTracker.footprint.dao.Footprint.FootprintDao;
import com.carbonTracker.footprint.dao.Home.HomeDao;
import com.carbonTracker.footprint.dao.RecommendationList.RecommendationListDao;
import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.dao.Vehicle.VehicleDao;
import com.carbonTracker.footprint.model.footprint.Footprint;
import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.recommendationList.RecommendationList;
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

    private final RecommendationListDao recommendationListDao;

    //User Endpoints
    @Autowired
    public FootprintController(UserDao userDao, VehicleDao vehicleDao, HomeDao homeDao, FootprintDao footprintDao, RecommendationListDao recommendationListDao){
        this.userDao = userDao;
        this.vehicleDao = vehicleDao;
        this.homeDao = homeDao;
        this.footprintDao = footprintDao;
        this.recommendationListDao = recommendationListDao;
    }

    @PostMapping("/add")
    public ResponseEntity<Void> addUser(@Valid @RequestBody User user, UriComponentsBuilder ucb){
        int savedUser = userDao.addUser(user);
        URI locationOfUser = ucb
                .path("footprint/add")
                .buildAndExpand(savedUser)
                .toUri();
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
            return ResponseEntity.ok(user.orElseThrow(() -> new RuntimeException("user not found")));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> updateUser(@Valid @RequestBody User user, @PathVariable("id") int id){
        Optional<User> findUser = userDao.findUserById(id);
        if(!findUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else {
            userDao.updateUser(id, user);
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/footprint/{id}")
    public ResponseEntity<Optional<Footprint>> getUserFootPrint(@PathVariable("id") int id){
        Optional <Footprint> footprint = footprintDao.getUserFootprint(id).stream().findFirst();
        if(footprint.isPresent()){
            return ResponseEntity.ok(footprint);
        }else{
            return ResponseEntity.notFound().build();
        }

    }
    @PostMapping("{id}/add/vehicle")
    public ResponseEntity<Void> addVehicleByUserId(@Valid @RequestBody Vehicle vehicle, @PathVariable("id") int id, UriComponentsBuilder ucb){
        Optional<User> findUser = userDao.findUserById(id);
        if(findUser.isPresent()) {
            int savedUserVehicle = vehicleDao.addUserVehicle(vehicle, id);
            URI locationOfVehicle = ucb
                    .path("footprint/{id}/add/vehicle")
                    .buildAndExpand(savedUserVehicle)
                    .toUri();
            return ResponseEntity.created(locationOfVehicle).build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/vehicle")
    public ResponseEntity<List<Vehicle>> getVehicleByUserId(@PathVariable("id") int id){
        Optional<User> findUser = userDao.findUserById(id);
        if(findUser.isPresent()){
            return ResponseEntity.ok(vehicleDao.findVehicleByUserId(id));
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}/update/vehicle")
    public ResponseEntity<Void> updateUserVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable int id){
        Optional<User> findUser = userDao.findUserById(id);
        if(findUser.isPresent()){
            vehicleDao.updateUserVehicle(vehicle, id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}/delete/vehicle")
    public ResponseEntity<Void> deleteVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable int id){
        vehicleDao.deleteVehicle(id, vehicle);
        return ResponseEntity.noContent().build();
    }

    //Home Endpoints
    @GetMapping("{id}/home")
    public ResponseEntity<List<Home>> getUserHomes(@PathVariable("id") int userId){
        Optional<User> findUser = userDao.findUserById(userId);
        if(findUser.isPresent()){
            return ResponseEntity.ok(homeDao.getUserHomes(userId));
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("{id}/home")
    public ResponseEntity<Void> addHome(@PathVariable("id") int userId, @Valid @RequestBody Home home, UriComponentsBuilder ucb ){
        Optional<User> findUser = userDao.findUserById(userId);
        if(findUser.isPresent()){
            int savedUserHome = homeDao.addHome(home, userId);
            URI locationOfHome = ucb
                    .path("footprint/{id}/home")
                    .buildAndExpand(savedUserHome)
                    .toUri();
            return ResponseEntity.created(locationOfHome).build();
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}/delete/home")
    public ResponseEntity<Void> deleteHome(@Valid @RequestBody Home home, @PathVariable("id") int userId){
        homeDao.deleteHome(home, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("recommendation")
    public ResponseEntity<List<RecommendationList>> getRecommendations(){
        return ResponseEntity.ok(recommendationListDao.getRecommendation());
    }



}
