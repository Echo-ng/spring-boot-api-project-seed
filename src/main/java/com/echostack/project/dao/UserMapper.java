package com.echostack.project.dao;

import com.echostack.project.model.entity.User;

import java.util.List;

public interface UserMapper {

    User findByUsername(String username);

    User add(User user);

    int deleteById(Long id);

    int update(User user);

    List<User> findAll();
}