package com.echostack.project.service.impl;

import com.echostack.project.dao.UserMapper;
import com.echostack.project.model.entity.User;
import com.echostack.project.service.UserService;
import com.echostack.project.infra.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by CodeGenerator on 2019/02/21.
 */
@Service
@Transactional
public class UserServiceImpl extends AbstractService<User> implements UserService,UserDetailsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("用户名不存在");
        }else{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        }
        return user;
    }
}
