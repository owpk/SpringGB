package com.owpk.hw.controllers;

import com.owpk.hw.entities.User;
import com.owpk.hw.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@AllArgsConstructor
public class MainController {

    private UserService userService;

    @GetMapping("/unsecured")
    @ResponseBody
    private String unsecured() {
        return "unsecured";
    }

    @GetMapping("/")
    @ResponseBody
    private String home() {
        return "home";
    }

    @GetMapping("/authed")
    @ResponseBody
    private String authed(Principal principal) {
        User u = userService.findByUsername(principal.getName());
        return "authed: " + u.getEmail();
    }

    @GetMapping("/admin")
    @ResponseBody
    private String admin() {
        return "admin";
    }

}
