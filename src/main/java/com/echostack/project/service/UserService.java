package com.echostack.project.service;

import com.echostack.project.infra.service.Service;
import com.echostack.project.model.dto.UserDto;
import com.echostack.project.model.vo.UserVo;

import java.util.List;

public interface UserService extends Service<UserDto> {

    UserDto getNameByName(UserDto userDto);

    UserVo toVo(UserDto userDto);

    List<UserVo> toVos(List<UserDto> userDtos);

}
