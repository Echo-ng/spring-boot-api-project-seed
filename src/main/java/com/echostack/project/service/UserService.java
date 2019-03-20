package com.echostack.project.service;

import com.echostack.project.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User add(User user);

    int deleteById(Long id);

    int update(User user);

    List<User> findAll();

}
