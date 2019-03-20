package com.echostack.project.service.impl;

import com.echostack.project.dao.UserMapper;
import com.echostack.project.model.entity.User;
import com.echostack.project.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by CodeGenerator on 2019/02/21.
 */

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService,UserDetailsService {
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

    public User findByUsername(String username){

        return userMapper.findByUsername(username);

    }

    @Override
    public User add(User user) {
        User result = null;
        try {
            result = userMapper.add(user);
        } catch (Exception e) {
            String errorMsg = "保存用户信息出错，"+e.getMessage();
            log.error(errorMsg);
        }
        return result;
    }

    @Override
    public int deleteById(Long id) {
        return this.userMapper.deleteById(id);
    }

    @Override
    public int update(User user) {
        return this.userMapper.update(user);
    }

    @Override
    public List<User> findAll() {
        return this.userMapper.findAll();
    }
}
