package com.echostack.project.dao;

import com.echostack.project.infra.mapper.Mapper;
import com.echostack.project.model.entity.User;

public interface UserMapper extends Mapper<User> {
    User findByUsername(String username);
}