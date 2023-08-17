package com.carbonTracker.footprint;

import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.dao.User.UserDaoImpl;
import com.carbonTracker.footprint.model.user.User;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;

@ExtendWith(MockitoExtension.class)
public class DaoTest {

    @Mock
    private UserDao dao;

    @InjectMocks
    private UserDaoImpl userDao;

    @Mock
    private JdbcTemplate jdbcTemplate;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
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
}
