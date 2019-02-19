package com.echostack.project.web;

import com.echostack.project.infra.dto.Result;
import com.echostack.project.infra.dto.ResultCode;
import com.echostack.project.model.dto.UserDto;
import com.echostack.project.model.vo.UserVo;
import com.echostack.project.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"测试组"})
@RestController
public class UserController {

    private Logger logger = Logger.getLogger(UserController.class);

    @Autowired
    UserService userService;

    @ApiOperation(value = "查询用户信息", notes = "根据用户名称查询用户信息")
    @RequestMapping(value = "/user/${name}", method = {RequestMethod.POST}
            , produces = {"application/json","application/xml"})
    public Result<UserVo> getUserByName(@ApiParam(required = true, value = "用户名")
                                @PathVariable(value = "name") String name) {
        Result<UserVo> result = new Result<>();
        UserDto userDto = new UserDto();
        userDto.setName(name);
        result.setCode(ResultCode.SUCCESS)
                .setMessage("查询成功")
                .setData(userService.toVo(userService.getNameByName(userDto)));
        return result;
    }

    @ApiOperation(value = "新增用户", notes = "新增用户")
    @RequestMapping(value = "/user/add", method = {RequestMethod.POST}
            , produces = {"application/json","application/xml"})
    public Result<UserDto> addUser(@ApiParam(required = true, value = "用户名")
                                        @RequestParam(value = "name") String name) {
        Result<UserDto> result = new Result<>();
        UserDto userDto = new UserDto();
        userDto.setName(name);
        result.setCode(ResultCode.SUCCESS)
                .setMessage("新增成功")
                .setData(userService.toVo(userService.findById(userService.save(userDto))));
        return result;
    }


}
