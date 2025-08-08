package com.abk.OAuth2Login.repositories;

import com.abk.OAuth2Login.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositories extends JpaRepository<User,Integer>
{
    public User findByUserEmail(String email);
}
