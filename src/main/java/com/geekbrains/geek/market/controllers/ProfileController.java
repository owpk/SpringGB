package com.geekbrains.geek.market.controllers;

import com.geekbrains.geek.market.dto.ProfileDto;
import com.geekbrains.geek.market.entities.Acc;
import com.geekbrains.geek.market.entities.User;
import com.geekbrains.geek.market.exceptions.MarketError;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.services.ProfileService;
import com.geekbrains.geek.market.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/profiles")
@AllArgsConstructor
public class ProfileController {
    private final ProfileService profileService;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(produces = "application/json")
    private ProfileDto edit(Principal principal) {
        Acc acc = profileService.findByUserName(principal.getName())
                .orElseThrow(() -> new ResourceNotFoundException("not found"));
        return new ProfileDto(acc);
    }

    @PutMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    private ResponseEntity<?> edited(Principal principal, @RequestBody ProfileDto profileDto) {
        User user = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException(""));
        if (profileDto.getConfirmationPassword() == null || !passwordEncoder.matches(profileDto.getConfirmationPassword(), user.getPassword())) {
            return new ResponseEntity<>(new MarketError(HttpStatus.BAD_REQUEST.value(), "Incorrect password"), HttpStatus.BAD_REQUEST);
        }
        Acc acc = profileService.findById(profileDto.getId()).orElseThrow(() -> new ResourceNotFoundException(""));
        profileService.saveOrUpdate(acc);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
