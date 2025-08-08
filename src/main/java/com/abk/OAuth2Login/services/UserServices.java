package com.abk.OAuth2Login.services;

import com.abk.OAuth2Login.model.User;

import java.util.Optional;

public interface UserServices  {
    public User saveUser(User user);
    public Optional<User> getUserProfile(int userId);
    public User getUserByEmail(String email);
}
