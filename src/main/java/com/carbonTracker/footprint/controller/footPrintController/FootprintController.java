package com.carbonTracker.footprint.controller.footPrintController;
import com.carbonTracker.footprint.dao.Footprint.FootprintDao;
import com.carbonTracker.footprint.dao.Home.HomeDao;
import com.carbonTracker.footprint.dao.Offsetters.OffSettersDao;
import com.carbonTracker.footprint.dao.RecommendationList.RecommendationListDao;
import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.dao.Vehicle.VehicleDao;
import com.carbonTracker.footprint.model.footprint.Footprint;
import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.recommendationList.RecommendationList;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/")
public class FootprintController {

    private final UserDao userDao;
    private final VehicleDao vehicleDao;
    private final HomeDao homeDao;
    private final FootprintDao footprintDao;
    private final RecommendationListDao recommendationListDao;

    private final OffSettersDao offSettersDao;

    //User Endpoints
    @Autowired
    public FootprintController(UserDao userDao, VehicleDao vehicleDao, HomeDao homeDao, FootprintDao footprintDao, RecommendationListDao recommendationListDao, OffSettersDao offSettersDao){
        this.userDao = userDao;
        this.vehicleDao = vehicleDao;
        this.homeDao = homeDao;
        this.footprintDao = footprintDao;
        this.recommendationListDao = recommendationListDao;
        this.offSettersDao = offSettersDao;
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
    public ResponseEntity<?> findById(@PathVariable("id") int id, Principal principal){
        Optional<User> user = userDao.findUserById(id);
        if(user.isPresent()){
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();
            if(!userEmail.equals(authEmail)){
                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
            return ResponseEntity.ok(user.orElseThrow(() -> new RuntimeException("user not found")));
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@Valid @RequestBody User user, @PathVariable("id") int id, Principal principal){

        Optional<User> findUser = userDao.findUserById(id);

        if(!findUser.isPresent()){
            return ResponseEntity.notFound().build();
        }
        else {
            String authEmail = principal.getName();
            String userEmail = user.getEmail();

            if(!userEmail.equals(authEmail)){
                userDao.updateUser(id, user);
                return ResponseEntity.noContent().build();
            }
            else {
                return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
            }
        }
    }

    @GetMapping("/footprint/{id}")
    public ResponseEntity<Optional<Footprint>> getUserFootPrint(@PathVariable("id") int id, Principal principal){
        List <Footprint> footprint = footprintDao.getUserFootprint(id);
        if(footprint.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        else{
            String authEmail = principal.getName();
            String userEmail = footprint.stream().findFirst().get().getUser().getEmail();

            if(authEmail.equals(userEmail)){
                return ResponseEntity.ok(footprint.stream().findFirst());
            }
            else{
               return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        }
    }
    @PostMapping("{id}/add/vehicle")
    public ResponseEntity<Void> addVehicleByUserId(@Valid @RequestBody Vehicle vehicle, @PathVariable("id") int id, UriComponentsBuilder ucb, Principal principal){
        Optional<User> user = userDao.findUserById(id);

        if(user.isPresent()) {
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();
            if(!authEmail.equals(userEmail)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else {
                int savedUserVehicle = vehicleDao.addUserVehicle(vehicle, id);
                URI locationOfVehicle = ucb
                        .path("footprint/{id}/add/vehicle")
                        .buildAndExpand(savedUserVehicle)
                        .toUri();
                return ResponseEntity.created(locationOfVehicle).build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("{id}/vehicle")
    public ResponseEntity<List<Vehicle>> getVehicleByUserId(@PathVariable("id") int id, Principal principal){
        Optional<User> user = userDao.findUserById(id);

        if(user.isPresent()){
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();

            if(!authEmail.equals(userEmail)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else {
                return ResponseEntity.ok(vehicleDao.findVehicleByUserId(id));
            }
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("{id}/update/vehicle")
    public ResponseEntity<Void> updateUserVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable int id, Principal principal){
        Optional<User> user = userDao.findUserById(id);
        if(user.isPresent()){
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();

            if(!authEmail.equals(userEmail)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                vehicleDao.updateUserVehicle(vehicle, id);
                return ResponseEntity.noContent().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("{id}/delete/vehicle")
    public ResponseEntity<Void> deleteVehicle(@Valid @RequestBody Vehicle vehicle, @PathVariable int id, Principal principal){
        Optional<User> user = userDao.findUserById(id);
        if(user.isPresent()) {
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();
            if (!authEmail.equals(userEmail)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else {
                vehicleDao.deleteVehicle(id, vehicle);
                return ResponseEntity.noContent().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //Home Endpoints
    @GetMapping("{id}/home")
    public ResponseEntity<List<Home>> getUserHomes(@PathVariable("id") int userId, Principal principal){
        Optional<User> user = userDao.findUserById(userId);
        if(user.isPresent()){
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();

            if(!authEmail.equals(userEmail)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }else {
                return ResponseEntity.ok(homeDao.getUserHomes(userId));
            }
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("{id}/home")
    public ResponseEntity<Void> addHome(@PathVariable("id") int userId, @Valid @RequestBody Home home, UriComponentsBuilder ucb, Principal principal ){
        Optional<User> user = userDao.findUserById(userId);
        if(user.isPresent()){
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();
            if(!authEmail.equals(userEmail)){
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
            else {
                int savedUserHome = homeDao.addHome(home, userId);
                URI locationOfHome = ucb
                        .path("footprint/{id}/home")
                        .buildAndExpand(savedUserHome)
                        .toUri();
                return ResponseEntity.created(locationOfHome).build();
            }
        } else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}/delete/home")
    public ResponseEntity<Void> deleteHome(@Valid @RequestBody Home home, @PathVariable("id") int userId, Principal principal){
        Optional<User> user = userDao.findUserById(userId);
        if(user.isPresent()) {
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();
            if (!authEmail.equals(userEmail)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                homeDao.deleteHome(home, userId);
                return ResponseEntity.noContent().build();
            }
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("recommendations")
    public ResponseEntity<List<RecommendationList>> getRecommendations(){
        return ResponseEntity.ok(recommendationListDao.getRecommendation());
    }

    @GetMapping("{id}/offsetters")
    public ResponseEntity<List<OffSetters>> getOffSetters(@PathVariable("id") int id, Principal principal){
        Optional<User> user = userDao.findUserById(id);

        if(user.isPresent()) {
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();

            if (!authEmail.equals(userEmail)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                return ResponseEntity.ok(offSettersDao.getOffSetters(id));
            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("{id}/offsetters")
    public ResponseEntity<Void> addOffSetters(@PathVariable("id") int id,@Valid @RequestBody OffSetters offSetters, UriComponentsBuilder ucb, Principal principal){
        Optional<User> user = userDao.findUserById(id);

        if(user.isPresent()) {
            String authEmail = principal.getName();
            String userEmail = user.get().getEmail();

            if (!authEmail.equals(userEmail)) {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            } else {
                int savedOffSetter = offSettersDao.addOffSetter(offSetters, id);
                URI locationOfOffSetter = ucb
                        .path("{id}/offsetter")
                        .buildAndExpand(savedOffSetter)
                        .toUri();
                return ResponseEntity.created(locationOfOffSetter).build();

            }
        }else {
            return ResponseEntity.notFound().build();
        }
    }

}
