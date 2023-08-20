package com.carbonTracker.footprint;

import com.carbonTracker.footprint.dao.Footprint.FootprintDaoImpl;
import com.carbonTracker.footprint.dao.Home.HomeDaoImpl;
import com.carbonTracker.footprint.dao.Offsetters.OffSettersDaoImpl;
import com.carbonTracker.footprint.dao.RecommendationList.RecommendationListDaoImpl;
import com.carbonTracker.footprint.dao.User.UserDaoImpl;
import com.carbonTracker.footprint.dao.Vehicle.VehicleDaoImpl;
import com.carbonTracker.footprint.dao.userImage.UserImageDaoImpl;
import com.carbonTracker.footprint.model.footprint.Footprint;
import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.recommendationList.RecommendationList;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.userImage.UserImage;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
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
    private OffSettersDaoImpl offSettersDao;

    @InjectMocks
    private RecommendationListDaoImpl recommendationListDao;

    @InjectMocks
    private UserImageDaoImpl userImageDao;

    @InjectMocks
    private FootprintDaoImpl footprintDao;

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

    @Test
    public void getRecommendationList(){
        RecommendationList recommendationList = new RecommendationList();
        recommendationList.setCCS(200);
        recommendationList.setType("Plant");
        recommendationList.setProductLocation("www.plant.com");
        recommendationList.setProduct("Succulent");

        List <RecommendationList> getList = new ArrayList<RecommendationList>();

        getList.add(recommendationList);

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<RecommendationList>>any())).thenReturn(getList);

        List <RecommendationList> findList = recommendationListDao.getRecommendation();

        Assertions.assertEquals(200, findList.stream().findFirst().get().getCCS());
    }

    @Test
    public void getOffsetters(){
        OffSetters offSetters = new OffSetters();
        offSetters.setUserId(1);
        offSetters.setCCS(200);
        offSetters.setProduct("Succulent");
        offSetters.setType("Plant");

        List <OffSetters> getList = new ArrayList<OffSetters>();
        getList.add(offSetters);

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<OffSetters>>any(), Mockito.anyInt())).thenReturn(getList);

        List <OffSetters> getOffSettersList = offSettersDao.getOffSetters(1);

        Assertions.assertEquals("Plant", getOffSettersList.stream().findFirst().get().getType());
    }

    @Test
    public void addOffsetter(){
        OffSetters offSetters = new OffSetters();
        offSetters.setUserId(1);
        offSetters.setCCS(200);
        offSetters.setProduct("Succulent");
        offSetters.setType("Plant");

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(),Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);

        int addOffsetter = offSettersDao.addOffSetter(offSetters,offSetters.getUserId());

        Assertions.assertEquals(1, addOffsetter);
    }

    @Test
    public void deleteOffSetter(){
        OffSetters offSetters = new OffSetters();
        offSetters.setUserId(1);
        offSetters.setCCS(200);
        offSetters.setProduct("Succulent");
        offSetters.setType("Plant");

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyInt(), Mockito.anyInt())).thenReturn(1);

        int deleteOffsetter = offSettersDao.deleteOffSetter(offSetters.getUserId(), offSetters);

        Assertions.assertEquals(1, deleteOffsetter);
    }

    @Test
    public void addUserImage(){
        byte[] bytes = { (byte) 204, 29, (byte) 207, (byte) 217 };
        UserImage userImage = new UserImage();
        userImage.setUserId(1);
        userImage.setImageName("dog");
        userImage.setType("png");
        userImage.setImageData(bytes);

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyInt())).thenReturn(1);

        int addUserImage = userImageDao.addUserImage(userImage, userImage.getUserId());
        Assertions.assertEquals(1,addUserImage);
    }

    @Test
    public void updateUserImage(){
        byte[] bytes = { (byte) 204, 29, (byte) 207, (byte) 217 };
        UserImage userImage = new UserImage();
        userImage.setUserId(1);
        userImage.setImageName("dog");
        userImage.setType("png");
        userImage.setImageData(bytes);

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyString(), Mockito.anyString(), Mockito.any(), Mockito.anyInt())).thenReturn(1);

        int updateUserImage = userImageDao.updateUserImage(1, userImage);
        Assertions.assertEquals(1, updateUserImage);

    }

    @Test
    public void findUserImage(){
        byte[] bytes = { (byte) 204, 29, (byte) 207, (byte) 217 };
        UserImage userImage = new UserImage();
        userImage.setUserId(1);
        userImage.setImageName("dog");
        userImage.setType("png");
        userImage.setImageData(bytes);

        List <UserImage> image = new ArrayList<UserImage>();
        image.add(userImage);

        Mockito.when(jdbcTemplate.query(Mockito.anyString(), ArgumentMatchers.<RowMapper<UserImage>>any(), Mockito.anyInt())).thenReturn(image);

        Optional <UserImage> getImage = userImageDao.findUserImage(1);
        Assertions.assertEquals("dog", getImage.get().getImageName());
    }

    @Test
    public  void deleteUserImage(){
        byte[] bytes = { (byte) 204, 29, (byte) 207, (byte) 217 };
        UserImage userImage = new UserImage();
        userImage.setUserId(1);
        userImage.setImageName("dog");
        userImage.setType("png");
        userImage.setImageData(bytes);

        Mockito.when(jdbcTemplate.update(Mockito.anyString(), Mockito.anyInt())).thenReturn(1);

        int deleteImage = userImageDao.deleteUserImage(1);
        Assertions.assertEquals(1, deleteImage);
    }

    @Test
    public void  getUserFootPrint(){

        User user = new User();
        user.setId(1);
        user.setFirstName("Test");
        user.setLastName("Testing");
        user.setEmail("Testing@test.com");
        user.setPassword("123");

        Home home = new Home();
        home.setUserId(1);
        home.setHomeSize(704);
        home.setHomeType("Condo");

        List<Home> homes = new ArrayList<Home>();
        homes.add(home);

        Vehicle vehicle = new Vehicle();
        vehicle.setId(1);
        vehicle.setMpg("0.0");
        vehicle.setType("SUV");
        vehicle.setUserId(1);

        List<Vehicle> vehicles = new ArrayList<Vehicle>();
        vehicles.add(vehicle);

        OffSetters offSetters = new OffSetters();
        offSetters.setUserId(1);
        offSetters.setCCS(200);
        offSetters.setProduct("Succulent");
        offSetters.setType("Plant");

        List<OffSetters> offSettersList = new ArrayList<OffSetters>();
        offSettersList.add(offSetters);

        Footprint footprint = new Footprint();
        footprint.setUser(user);
        footprint.setVehicles(vehicles);
        footprint.setOffSetters(offSettersList);
        footprint.setHomes(homes);

        List <Footprint> footprintList = new ArrayList<Footprint>();
        footprintList.add(footprint);

        Mockito.when(jdbcTemplate.query(Mockito.anyString(),ArgumentMatchers.<ResultSetExtractor<List>>any(), Mockito.anyInt())).thenReturn(footprintList);
        List <Footprint> getFootPrint = footprintDao.getUserFootprint(1);

       Assertions.assertEquals(1, getFootPrint.stream().findFirst().get().getUser().getId());
    }

}