package com.carbonTracker.footprint;

import com.carbonTracker.footprint.model.home.Home;
import com.carbonTracker.footprint.model.offSetters.OffSetters;
import com.carbonTracker.footprint.model.user.User;
import com.carbonTracker.footprint.model.vehicle.Vehicle;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.AfterClass;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureMockMvc
public class FootprintControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private TestRestTemplate restTemplate;


    @Test
    @AfterClass
    void deleteUser() throws Exception{

        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseDeleteUser= mockMvc.perform(delete("/api/{id}", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)));

        responseDeleteUser.andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    @DirtiesContext
    @Order(1)
    void shouldSignUserUp(){

        User user = new User();
        user.setEmail("TA");
        user.setPassword("12353");

        ResponseEntity<?> response = restTemplate
                .postForEntity("/api/signup", user, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        ResponseEntity<?> response2 = restTemplate
                .postForEntity("/api/signup", user, Void.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
    @Test
    @DirtiesContext
    void shouldSignInUser(){

        User user = new User();
        user.setEmail("TA");
        user.setPassword("12353");

        User user2 = new User();
        user2.setFirstName("T");
        user2.setLastName("A");
        user2.setEmail("TA");
        user2.setPassword("NotMyPassword");

        ResponseEntity<String> response = restTemplate
                .postForEntity("/api/signin", user, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<String> response2 = restTemplate
                .postForEntity("/api/signin", user2, String.class);

        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }


    @Test
    void shouldGetUserByEmail() throws Exception {

        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

                ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        responseGetUserByEmail.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", is(userId)))
                .andExpect(jsonPath("$.email", is(user.getEmail())));
    }

    @Test
    @Order(2)
    void shouldUpdateUser() throws Exception{

        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseUpdateUser= mockMvc.perform(put("/api/update/{id}", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        responseUpdateUser.andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    void shouldGetUserById() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetUserById= mockMvc.perform(get("/api/{id}", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        responseGetUserById.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", is(userId)))
                .andExpect(jsonPath("$.firstName",is(user.getFirstName())))
                .andExpect(jsonPath("$.lastName",is(user.getLastName())))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

    }

    @Test
    @Order(3)
    void shouldAddVehicleInfo() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        Vehicle vehicle = new Vehicle();
        vehicle.setType("SUV");
        vehicle.setMpg("40.0");
        vehicle.setUserId(userId);

        ResultActions responseAddVehicle = mockMvc.perform(post("/api/{id}/add/vehicle", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicle))
        );

        responseAddVehicle.andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    @Order(5)
    void shouldUpdateVehicleInfo() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        Vehicle vehicle = new Vehicle();
        vehicle.setType("Sedan");
        vehicle.setMpg("25.0");
        vehicle.setUserId(userId);

        ResultActions responseAddVehicle = mockMvc.perform(put("/api/{id}/update/vehicle", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicle))
        );

        responseAddVehicle.andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    @Order(4)
    void shouldGetVehicleInfo() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetVehicle = mockMvc.perform(get("/api/{id}/vehicle", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        responseGetVehicle.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$" ).isNotEmpty())
                .andExpect(jsonPath("$[0].userId", is(userId)))
                .andExpect(jsonPath("$[0].type", is("SUV")))
                .andExpect(jsonPath("$[0].mpg", is("40.0")));

    }

    @Test
    @Order(5)
    void shouldDeleteVehicleInfo() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetVehicle = mockMvc.perform(get("/api/{id}/vehicle", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        Integer vehicleId = JsonPath.read(responseGetVehicle.andReturn().getResponse().getContentAsString(), "$[0].id");

        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleId);
        vehicle.setType("Sedan");
        vehicle.setMpg("25.0");
        vehicle.setUserId(userId);

        ResultActions responseDeleteVehicle = mockMvc.perform(delete("/api/{id}/delete/vehicle", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(vehicle))
        );

        responseDeleteVehicle.andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    @Order(6)
    void shouldAddHome() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        Home home = new Home();
        home.setHomeSize(750);
        home.setHomeType("condo");
        home.setUserId(userId);


        ResultActions responseAddUserHome = mockMvc.perform(post("/api/{id}/home",userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(home))
        );

        responseAddUserHome.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(7)
    void shouldGetUserHome() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetHome = mockMvc.perform(get("/api/{id}/home", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        responseGetHome.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].homeType", is("condo")))
                .andExpect(jsonPath("$[0].homeSize", is(750)))
                .andExpect(jsonPath("$[0].userId", is(userId)));
    }

    @Test
    @Order(8)
    void shouldDeleteHome()throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetHome = mockMvc.perform(get("/api/{id}/home", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );


        Integer homeId = JsonPath.read(responseGetHome.andReturn().getResponse().getContentAsString(), "$[0].id");

        Home home = new Home();
        home.setId(homeId);
        home.setHomeSize(750);
        home.setHomeType("condo");
        home.setUserId(userId);

        ResultActions responseDeleteHome = mockMvc.perform(delete("/api/{id}/delete/home", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(home))
        );

        responseDeleteHome.andDo(print())
                .andExpect(status().isNoContent());

    }

    @Test
    @Order(10)
    void shouldAddOffSetters() throws Exception {
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        OffSetters offSetters = new OffSetters();
        offSetters.setType("Plant");
        offSetters.setCCS(5);
        offSetters.setProduct("Succulent");
        offSetters.setUserId(userId);


        ResultActions responseAddOffSetters = mockMvc.perform(post("/api/{id}/offsetters",userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offSetters))
        );

        responseAddOffSetters.andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    @Order(11)
    void shouldGetOffsetters() throws Exception{

        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseOffSetters = mockMvc.perform(get("/api/{id}/offsetters", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        responseOffSetters.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$[0].type", is("Plant")))
                .andExpect(jsonPath("$[0].product", is("Succulent")))
                .andExpect(jsonPath("$[0].CCS", is(5)))
                .andExpect(jsonPath("$[0].userId", is(userId)));

    }

    @Test
    @Order(12)
    void shouldDeleteOffsetters() throws Exception{

        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetOffSetter = mockMvc.perform(get("/api/{id}/offsetters", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );


        Integer offSetterId = JsonPath.read(responseGetOffSetter.andReturn().getResponse().getContentAsString(), "$[0].id");

        OffSetters offSetters = new OffSetters();
        offSetters.setId(offSetterId);
        offSetters.setType("type");
        offSetters.setCCS(5);
        offSetters.setProduct("Succulent");
        offSetters.setUserId(userId);

        ResultActions responseDeleteHome = mockMvc.perform(delete("/api/{id}/offsetters", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(offSetters))
        );

        responseDeleteHome.andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(13)
    void shouldAddFile() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        ResultActions responseAddUserImage = mockMvc.perform(multipart("/api/{id}/upload", userId).file(file)
                        .with(user(user.getEmail()).password(user.getPassword())))
                    .andExpect(status().isOk());


    }

    @Test
    @Order(14)
    void shouldGetImage() throws Exception{

        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseGetUserImage = mockMvc.perform(get("/api/{id}/image", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        responseGetUserImage.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    @Order(15)
    void shouldDeleteImage() throws Exception{
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResultActions responseGetUserByEmail= mockMvc.perform(get("/api/email")
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user))
        );

        Integer userId = JsonPath.read(responseGetUserByEmail.andReturn().getResponse().getContentAsString(), "$.id");

        ResultActions responseDeleteHome = mockMvc.perform(delete("/api/{id}/image", userId)
                .with(user(user.getEmail()).password(user.getPassword()))
                .contentType(MediaType.APPLICATION_JSON)
        );

        responseDeleteHome.andDo(print())
                .andExpect(status().isNoContent());
    }
}
