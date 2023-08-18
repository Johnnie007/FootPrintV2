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

import java.util.*;

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

    @Test
    public void findUserByEmail(){
        String sql = """
                SELECT id, email, password
                From user
                WHERE email = ?
                """;

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
        String sql = """
                UPDATE user
                SET first_name = ?, last_name = ?, email = ?, footPrint = ?, lifeStyle = ?
                WHERE id = ?;
                """;

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


}
