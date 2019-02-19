package com.echostack.project.service.impl;

import com.echostack.project.dao.UserDao;
import com.echostack.project.infra.service.AbstractService;
import com.echostack.project.model.dto.UserDto;
import com.echostack.project.model.vo.UserVo;
import com.echostack.project.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl extends AbstractService<UserDto> implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDto getNameByName(UserDto userDto) {
        return this.findBy("name",userDto.getName());
    }

    @Override
    public UserVo toVo(UserDto userDto) {
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(userDto,userVo);
        return userVo;
    }

    @Override
    public List<UserVo> toVos(List<UserDto> userDtos) {
        List<UserVo> userVos = new ArrayList<>();
        userDtos.stream().forEach(userDto -> {
            userVos.add(this.toVo(userDto));
        });
        return userVos;
    }
}
