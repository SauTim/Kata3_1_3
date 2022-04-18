package com.example.kata_3_1_2ver2.controllers;

import com.example.kata_3_1_2ver2.entities.User;
import com.example.kata_3_1_2ver2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public String showOneUser(Model model, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        model.addAttribute(user);
        return "user/userInfo";
    }

}
