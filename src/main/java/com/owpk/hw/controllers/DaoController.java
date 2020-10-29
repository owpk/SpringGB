package com.owpk.hw.controllers;
import com.owpk.hw.entities.User;
import com.owpk.hw.exceptions.GeneralAppException;
import com.owpk.hw.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class DaoController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/authorities")
    public String checkAuthorities() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }

    @GetMapping("/principals")
    public String checkPrincipal(Principal principal) {
        if (principal == null)
            throw new GeneralAppException("unable to load principals");
        return principal.getName();
    }

    @GetMapping("/dao")
    public String daoTestPage(Principal principal) {
        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        if (principal == null)
            throw new GeneralAppException("unable to find user by username");
        User user = userService.findByUsername(principal.getName());
        return "authenticated: " + user.getUsername() + " : " + user.getEmail();
    }
}
