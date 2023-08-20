package com.example.admin.user.controller;

import com.example.admin.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @PostMapping("/users/check_email")
    public String checkDuplicateEmail(@RequestParam("email") String email, @RequestParam("id") Integer id) {
        return userService.isEmailUnique(id, email) ? "OK" : "DUPLICATED";
    }
}
