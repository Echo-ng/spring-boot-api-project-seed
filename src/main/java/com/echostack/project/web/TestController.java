package com.echostack.project.web;

import com.echostack.project.infra.dto.Result;
import com.echostack.project.infra.dto.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Echo
 * @Date: 2019/2/19 16:52
 * @Description:
 */
@Api(tags = {"测试组"})
@RestController
public class TestController {

    @ApiOperation(value = "登录", notes = "进入系统前需要登录")
    @RequestMapping(value = "/login", method = {RequestMethod.POST}
            , produces = {"application/json","application/xml"})
    public Result<String> login(@ApiParam(required = true, value = "用户名")
                            @RequestParam(value = "username") String username
            , @ApiParam(required = true, value = "验证码")
                            @RequestParam(value = "checkCode", required = true) String checkCode) {
        Result<String> result = new Result<>();
        result.setCode(ResultCode.SUCCESS).setMessage("会员:"+username+"，登录成功！");
        result.setData("SUCCESS");
        return result;
    }
}
