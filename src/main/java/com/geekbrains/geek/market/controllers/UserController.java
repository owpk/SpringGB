package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.configs.JwtTokenUtil;
import com.geekbrains.geek.market.dto.JwtRequest;
import com.geekbrains.geek.market.dto.JwtResponse;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.MarketError;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;

    @PostMapping("/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody JwtRequest authRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        } catch (BadCredentialsException ex) {
            return new ResponseEntity<>(new MarketError(HttpStatus.UNAUTHORIZED.value(), "Incorrect username or password"), HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    //Edit profile
    @GetMapping("/edit")
    public ResponseEntity<?> editAccResponse(Principal principal) {
        User u = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        System.out.println(u.getUsername());
        System.out.println(principal.getName());
        return ResponseEntity.ok(u);
    }

    //Confirm edit profile
    @PostMapping("/edit")
    public void editConfirm(@RequestBody User user) {
        userService.saveOrUpdate(user);
    }
}
