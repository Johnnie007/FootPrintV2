package com.carbonTracker.footprint.customUserDetaislService;

import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.model.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.Set;

public class CustomUserDetailsService implements UserDetailsService {

    private UserDao userDao;
    public CustomUserDetailsService(UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        User user = userDao.findUserEmail(email).orElseThrow(() -> new UsernameNotFoundException("Email not found" + email));

       // return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), );
        return null;
    }


}
