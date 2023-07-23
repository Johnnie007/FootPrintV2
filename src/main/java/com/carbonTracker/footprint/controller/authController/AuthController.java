package com.carbonTracker.footprint.controller.authController;

import com.carbonTracker.footprint.dao.User.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManger;

    @Autowired
    private UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

   // @PostMapping()
}
