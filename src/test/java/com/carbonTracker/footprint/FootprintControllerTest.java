package com.carbonTracker.footprint;

import com.carbonTracker.footprint.model.user.User;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FootprintControllerTest {

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

        ResponseEntity<?> respons2 = restTemplate
                .postForEntity("/api/signup", user, Void.class);
        assertThat(respons2.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
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

}
