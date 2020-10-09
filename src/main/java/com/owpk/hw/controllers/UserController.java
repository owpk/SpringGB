package com.owpk.hw.controllers;

import com.owpk.hw.entities.User;
import com.owpk.hw.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.boot.context.properties.source.ConfigurationPropertyName;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class UserController {

    private UserService userService;

//    private SecurityService securityService;

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("user", new User());
        return "reg";
    }

    @PostMapping("/registration")
    public String registration(@RequestParam(name = "password") String password,
                               @RequestParam(name = "username") String username) {
//        if (bindingResult.hasErrors())
//            return "registration";
        User u = new User();
        u.setUsername(username);
        u.setPassword(password);
        userService.save(u);
//        securityService.autoLogin(user.getUsername(), user.getPassword());
        return "redirect:/products";
    }
}
