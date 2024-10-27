package com.example.demo.controller.auth;

import com.example.demo.dto.Login;
import com.example.demo.dto.Register;
import com.example.demo.model.user.User;
import com.example.demo.service.user.UsersService;
import com.example.demo.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UsersService usersService;
    @Autowired
    private JwtUtil jwtUtil;

        @PostMapping("/login")
    public String authenticate(@RequestBody Login login) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
            return jwtUtil.generateToken(login.getEmail());
        } catch (AuthenticationException e) {
            return "Failed";
        }
    }


        @PostMapping("/register")
    public String register(@RequestBody Register register) {
        if (usersService.existsByEmail(register.getEmail())) {
            return "Email already exists";
        }


        String encodedPassword = passwordEncoder.encode(register.getPassword());

        User newUser = new User();
        newUser.setEmail(register.getEmail());
        newUser.setPassword(encodedPassword);
        usersService.createUser(newUser);

        return "User registered";
    }
}
