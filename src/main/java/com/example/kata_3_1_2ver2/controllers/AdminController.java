package com.example.kata_3_1_2ver2.controllers;

import com.example.kata_3_1_2ver2.entities.Role;
import com.example.kata_3_1_2ver2.entities.User;
import com.example.kata_3_1_2ver2.services.RoleService;
import com.example.kata_3_1_2ver2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private UserService userService;
    private RoleService roleService;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/")
    public String showAdminPanel(Model model, Principal principal) {
        List<User> users = userService.getAllUsers();
        User user = userService.findByUsername(principal.getName());
        model.addAttribute("users", users);
        model.addAttribute("user", user);
        return "admin/adminPanel";
    }

    @GetMapping("/add")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());
        return "admin/addUser";
    }

    @PostMapping("/saveUser")
    public String saveUser(@RequestParam("username") String name, @RequestParam("password") String password,
                           @RequestParam("roles") String role, @RequestParam("salary") int salary) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(role));
        userService.saveUser(new User(name, userService.encode(password), salary, roles));
        return "redirect:/admin/";
    }

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "admin/updateUser";
    }

    @PostMapping("/update")
    public String updateUser(@RequestParam("id") Long id, @RequestParam("username") String name, @RequestParam("password") String password,
                             @RequestParam("roles") String role, @RequestParam("salary") int salary) {
        Set<Role> roles = new HashSet<>();
        roles.add(roleService.findByName(role));
        userService.updateUser(new User(id, name, password, salary, roles));
        return "redirect:/admin/";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return "redirect:/admin/";
    }
}