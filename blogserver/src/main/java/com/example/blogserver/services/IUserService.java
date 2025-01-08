package com.example.blogserver.services;

import com.example.blogserver.model.User;

import java.util.List;
import java.util.Optional;


public interface IUserService {

    public int register(User user);

    public Optional<User> findByUsername(String username);

    public List<User> allUsers();

}
