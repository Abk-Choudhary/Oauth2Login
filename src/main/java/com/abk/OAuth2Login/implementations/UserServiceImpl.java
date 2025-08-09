package com.abk.OAuth2Login.implementations;

import com.abk.OAuth2Login.model.User;
import com.abk.OAuth2Login.repositories.UserRepositories;
import com.abk.OAuth2Login.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserServices {

    private UserRepositories userRepositories;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepositories userRepositories, PasswordEncoder passwordEncoder) {
        this.userRepositories = userRepositories;
        this.passwordEncoder = passwordEncoder;
    }


    public UserServiceImpl(UserRepositories userRepositories) {
        this.userRepositories = userRepositories;
    }

    @Override
    public User saveUser(User user) {
        //System.out.println(user.getUserEmail());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole("ROLE_USER");
        if(user.getUserEmail().toLowerCase().contains("abk"))
        {
            user.setRole("ROLE_ADMIN");
        }
        return userRepositories.save(user);
    }

    @Override
    public Optional<User> getUserProfile(int userId) {
        return userRepositories.findById(userId);
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepositories.findByUserEmail(email);
    }
}
