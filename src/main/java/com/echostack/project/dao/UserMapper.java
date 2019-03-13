package com.echostack.project.dao;

import com.echostack.project.model.entity.User;

public interface UserMapper {

    User findByUsername(String username);
}