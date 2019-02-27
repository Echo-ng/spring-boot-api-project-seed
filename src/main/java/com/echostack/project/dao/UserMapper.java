package com.echostack.project.dao;

import com.echostack.project.infra.mapper.Mapper;
import com.echostack.project.model.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends Mapper<User> {

    @Select("SELECT * FROM T_USER where username = #{username}")
    User findByUsername(String username);
}