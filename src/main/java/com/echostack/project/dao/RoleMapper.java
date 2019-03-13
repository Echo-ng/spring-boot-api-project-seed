package com.echostack.project.dao;

import com.echostack.project.model.entity.Role;

/**
 * @Author: Echo
 * @Date: 2019/2/26 15:25
 * @Description:
 */
public interface RoleMapper {

    Role selectByName(String name);

}
