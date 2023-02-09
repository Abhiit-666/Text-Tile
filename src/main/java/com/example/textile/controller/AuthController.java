package com.example.textile.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.textile.Service.UserService;
import com.example.textile.model.User;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public boolean login(@RequestBody User user) {
        User found = userService.login(user.getUsername(), user.getPassword());
        if (found == null) {
            return false;
        }

        return true;
    }

    // fuctionality such that if the user tries to login and fails he is asked if he
    // wants to sigup; so signup is only sigup, no need for rechecking the existence
    // of the user
    @PostMapping("/signup")
    public boolean signup(@RequestBody User user) {
        userService.save(user);
        return true;
    }
}
