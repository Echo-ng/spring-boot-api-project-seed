package com.echostack.project.dao;

import com.echostack.project.model.dao.UserDo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao extends com.echostack.project.infra.mapper.Mapper<UserDo> {

}
