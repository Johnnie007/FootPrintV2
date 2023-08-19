package com.carbonTracker.footprint;

import com.carbonTracker.footprint.dao.Home.HomeDaoImpl;
import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.dao.User.UserDaoImpl;
import com.carbonTracker.footprint.dao.Vehicle.VehicleDaoImpl;
import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.home.HomeRowMapper;
import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.*;

@ExtendWith(MockitoExtension.class)
public class DaoTest {

    @InjectMocks
    private UserDaoImpl userDao;

    @InjectMocks
    private HomeDaoImpl homeDao;

    @InjectMocks
    private VehicleDaoImpl vehicleDao;

    @InjectMocks
    private OffSetters offSetters;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @ParameterizedTest
    @ValueSource(ints = 1)
    public void deleteUser(int id){
        int userId = 1;
        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyInt())).thenReturn(1);
        int deleteUser = userDao.deleteUser(userId);

        Assertions.assertEquals(1,deleteUser);
    }

    @Test
    public void createUserDaoTest(){
         String  sql = """
                INSERT into user(first_name, last_name, email, password)
                VALUES (?,?,?,?)
                """;
        User user = new User();
        user.setFirstName("Test");
        user.setLastName("Testing");
        user.setEmail("Testing@test.com");
        user.setPassword("123");

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyString())).thenReturn(1);

        int userCreated = userDao.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());

        Assertions.assertEquals(1,jdbcTemplate.update(sql, user.getFirstName() ,user.getLastName(), user.getEmail(), user.getPassword()));
        Assertions.assertEquals(1, userCreated);

    }

    @Test
    public void findUserByEmail(){

        String email = "Testing@test.com";

        List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", email);
        list.add(map);

        Mockito.when(jdbcTemplate.queryForList(Mockito.anyString(), Mockito.any(Object.class))).thenReturn(list);

        Optional<User> getUser = userDao.findUserEmail(email);
        Assertions.assertEquals(email, getUser.get().getEmail());
    }

    @Test
    public void updateUser(){
        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("Testing");
        user.setEmail("Testing@test.com");
        user.setFootPrint(293);
        user.setPassword("123");
        user.setLifeStyle("HomeBody");


        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(),Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyString(), Mockito.anyInt())).thenReturn(1);
        int updateUser = userDao.updateUser(user.getId(),user);

        Assertions.assertEquals(1,updateUser);
    }

    @Test
    public void getUserHome(){
        Home home = new Home();
        home.setUserId(1);
        home.setHomeSize(704);
        home.setHomeType("Condo");

        List<Home> homes = new ArrayList<Home>();
        homes.add(home);

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<Home>>any(), Mockito.anyInt())).thenReturn(homes);
        List<Home> getHomes = homeDao.getUserHomes(home.getUserId());

        Assertions.assertEquals(704, getHomes.stream().findFirst().get().getHomeSize());
    }

    @Test
    public void addHome(){
        Home home = new Home();
        home.setUserId(1);
        home.setHomeSize(704);
        home.setHomeType("Condo");

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(),  Mockito.anyInt())).thenReturn(1);

        int addHome = homeDao.addHome(home, 1);

        Assertions.assertEquals(1,addHome);
    }

    @Test
    public void updateHome(){
        Home home = new Home();
        home.setUserId(1);
        home.setHomeSize(1000);
        home.setHomeType("Apartment");

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);

        int updateHome = homeDao.updateHome(home, 1);

        Assertions.assertEquals(1, updateHome);
    }

    @Test
    public void deleteHome(){
        Home home = new Home();
        home.setUserId(1);
        home.setHomeSize(704);
        home.setHomeType("Condo");

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);
        int deleteHome = homeDao.deleteHome(home, 1);

        Assertions.assertEquals(1,deleteHome);
    }

    @Test
    public void addVehicle(){

        Vehicle vehicle = new Vehicle();

        vehicle.setMpg("32.4");
        vehicle.setType("SUV");
        vehicle.setUserId(1);

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt())).thenReturn(1);

        int addVehicle = vehicleDao.addUserVehicle(vehicle,1);

        Assertions.assertEquals(1,addVehicle);
    }

    @Test
    public void updateVehicle(){

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setMpg("0.0");
        vehicle.setType("SUV");
        vehicle.setUserId(1);

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);

        int updateUserVehicle = vehicleDao.updateUserVehicle(vehicle, 1);
        Assertions.assertEquals(1, updateUserVehicle);
    }

    @Test
    public void findVehicleByUserId(){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setMpg("0.0");
        vehicle.setType("SUV");
        vehicle.setUserId(1);

        List <Vehicle> vehicles= new ArrayList<Vehicle>();
        vehicles.add(vehicle);


        Mockito.when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<Vehicle>>any(), Mockito.anyInt())).thenReturn(vehicles);
        List <Vehicle> getVehicle = vehicleDao.findVehicleByUserId(1);
        Assertions.assertEquals(vehicle.getType(), getVehicle.stream().findFirst().get().getType());
    }


    @Test
    public void deleteVehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setMpg("0.0");
        vehicle.setType("SUV");
        vehicle.setUserId(1);

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);

        int deleteVehicle = vehicleDao.deleteVehicle(vehicle.getUserId(), vehicle);

        Assertions.assertEquals(1, deleteVehicle);
    }


}
