package org.example.demo.controller;


import org.example.demo.model.User;
import org.springframework.web.bind.annotation.*;
import org.example.demo.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/getUser")
    public User getUserByAuthentication(@RequestBody User user) {
        return userService.saveUser(user);
    }

}
