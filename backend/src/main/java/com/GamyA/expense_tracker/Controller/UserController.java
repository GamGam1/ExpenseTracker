package com.GamyA.expense_tracker.Controller;

import com.GamyA.expense_tracker.DTO.UserLogIn;
import com.GamyA.expense_tracker.DTO.UserRegister;
import com.GamyA.expense_tracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public String registerUser(@RequestBody UserRegister userRegister){
        userService.registerUser(userRegister);
        return "ok";
    }

    @PostMapping(value = "/login")
    public String userLogIn(@RequestBody UserLogIn userLogIn){
        return userService.AuthAndGetToken(userLogIn);
    }
}
