package com.staxter.user.controller;

import com.staxter.user.model.User;
import com.staxter.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller Class Responsible for get JSONObject , send to service layer and after that return expected response to client.
 */
@RestController
@RequestMapping(value = "/userservice")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * responsible for register user, send user object to service layer and {@link UserService}
     * @param user
     * @return expected user object
     */
    @PostMapping(value = "/register")
    public User saveUser(@RequestBody User user){
        return userService.registerUser(user);
    }
}
