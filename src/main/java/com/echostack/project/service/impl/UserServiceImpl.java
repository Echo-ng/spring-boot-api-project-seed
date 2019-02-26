package com.echostack.project.service.impl;

import com.echostack.project.dao.UserMapper;
import com.echostack.project.model.entity.User;
import com.echostack.project.service.UserService;
import com.echostack.project.infra.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

//    @Override
//    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//        User user = userMapper.findByUsername(s);
//        if (user == null) {
//            throw new UsernameNotFoundException("用户名不存在");
//        }
//        return user;
//    }
}
