package com.echostack.project.dao;

import com.echostack.project.infra.mapper.Mapper;
import com.echostack.project.model.entity.Role;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: Echo
 * @Date: 2019/2/26 15:25
 * @Description:
 */
public interface RoleMapper extends Mapper<Role>  {

    @Select("select * from role where name = #{name}")
    Role selectByName(String name);

}
