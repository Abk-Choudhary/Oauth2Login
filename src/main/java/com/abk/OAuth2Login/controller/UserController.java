package com.abk.OAuth2Login.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/public/home")
    public String openHome()
    {
        return "Home Page!";
    }

    @GetMapping("/private/user/profile")
    public String userProfile()
    {
        return "Hey User";

    }
}
