package com.carbonTracker.footprint;

import com.carbonTracker.footprint.model.user.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    TestRestTemplate restTemplate;

    @Test
    @DirtiesContext
    @Order(1)
    void shouldSignUserUp(){

        User user = new User();
        user.setEmail("TA");
        user.setPassword("12353");

        ResponseEntity<?> response = restTemplate
                .postForEntity("/api/signup", user, Void.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

        ResponseEntity<?> response2 = restTemplate
                .postForEntity("/api/signup", user, Void.class);
        assertThat(response2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
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
    void shouldGetAllUsers() throws Exception {
        User user = new User();
        user.setFirstName("T");
        user.setLastName("A");
        user.setEmail("TA");
        user.setPassword("12353");

        ResponseEntity<List<Map<String, Object>>> response = restTemplate
                .withBasicAuth(user.getEmail(),user.getPassword())
                .getForEntity("/api/all", any());

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);

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
                .content(objectMapper.writeValueAsString(user))
        );

        responseGetUserById.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isNotEmpty())
                .andExpect(jsonPath("$.id", is(userId)))
                .andExpect(jsonPath("$.email", is(user.getEmail())));

    }



}
