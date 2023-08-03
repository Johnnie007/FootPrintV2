package com.carbonTracker.footprint.service;

import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.model.user.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;
    public CustomUserDetailsService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        User users = (User) userDao.findUserEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));

        UserDetails userDetails = org.springframework.security.core.userdetails.User.builder()
                .username(users.getEmail())
                .password(users.getPassword())
                .roles()
                .build();

        return userDetails;
    }


}
