package com.carbonTracker.footprint.controller.authController;

import com.carbonTracker.footprint.dao.User.UserDao;
import com.carbonTracker.footprint.model.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin()
@RestController
@RequestMapping("/api/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManger;

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody User user){
        if(!userDao.findUserEmail(user.getEmail()).isEmpty()){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.OK);
        }

        userDao.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
        return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<String> authenicateUser(@RequestBody User user){

            Authentication authentication = authenticationManger.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return new ResponseEntity<>("User sign-in successfully", HttpStatus.OK);
    }
}
