package com.echostack.project.web;

import com.echostack.project.infra.dto.Result;
import com.echostack.project.infra.dto.ResultCode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Echo
 * @Date: 2019/2/19 16:52
 * @Description:
 */
@Api(tags = {"测试组"})
@RestController
public class TestController {

    @ApiOperation(value = "登录", notes = "进入系统前需要登录")
    @PostMapping("/signin")
    public Result<String> login(@ApiParam(required = true, value = "用户名")
                            @RequestParam(value = "username") String username
            , @ApiParam(required = true, value = "验证码")
                            @RequestParam(value = "password", required = true) String password) {
        Result<String> result = new Result<>();
        result.setCode(ResultCode.SUCCESS).setMessage("会员:"+username+"，登录成功！");
        result.setData("SUCCESS");
        return result;
    }


    private RequestCache requestCache = new HttpSessionRequestCache();
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @GetMapping("/authentication/require")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html"))
                redirectStrategy.sendRedirect(request, response, "/login");
        }
        return "访问的资源需要身份认证！";
    }
}
