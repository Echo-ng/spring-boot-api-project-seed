package com.echostack.project.service.impl;

import com.echostack.project.dao.UserMapper;
import com.echostack.project.model.User;
import com.echostack.project.service.UserService;
import com.echostack.project.infra.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by CodeGenerator on 2019/02/21.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService {
    @Autowired
    private UserMapper userMapper;

}
