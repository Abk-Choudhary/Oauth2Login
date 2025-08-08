package com.abk.OAuth2Login.controller;

import com.abk.OAuth2Login.forms.UserRegForm;
import com.abk.OAuth2Login.helper.Mapper;
import com.abk.OAuth2Login.implementations.UserServiceImpl;
import com.abk.OAuth2Login.model.User;
import com.abk.OAuth2Login.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
// Allow requests from a specific origin
public class UserController {

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    private UserServiceImpl userService;

    UserServices userServices;

    @GetMapping("/public/home")
    public String openHome()
    {
        return "Home Page!";
    }

    @GetMapping("/private/user/profile/{userId}")
    public Optional<User> userProfile(@PathVariable int userId)
    {
        return userService.getUserProfile(userId);

    }@GetMapping("/private/user/profile")
    public User userProfile2(Authentication authentication)
    {
      DefaultOAuth2User defaultOAuth2User =
              (DefaultOAuth2User) authentication.getPrincipal();
     String email =defaultOAuth2User.getAttribute("email");

     User user =userService.getUserByEmail(email);
        //System.out.println(email);
        //System.out.println(user.getName());
        return user;    //userService.getUserProfileByName(userId);

    }

    @PostMapping("/public/user/register")
    public ResponseEntity<String> addUser(@RequestBody UserRegForm userRegForm)
    {
        //System.out.println("------");
        //System.out.println(userRegForm.getRegEmail());
        User user = Mapper.mapToUser(userRegForm);
       // System.out.println(userService.getUserByEmail(user.getUserEmail()));

        if (userService.getUserByEmail(user.getUserEmail())==null)
        {
            userService.saveUser(Mapper.mapToUser(userRegForm));
            return new ResponseEntity< >("user created succesfully", HttpStatus.CREATED);
        }

        return new ResponseEntity< >("user already exist", HttpStatus.CREATED);
    }
}
