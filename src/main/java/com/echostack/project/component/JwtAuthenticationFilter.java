package com.echostack.project.component;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.echostack.project.infra.util.JwtTokenUtil;
import com.echostack.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {


    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        //获取预备token，带有头前缀的
        String preToken = httpServletRequest.getHeader(jwtTokenUtil.getHeaderKey());
        if (preToken != null && preToken.startsWith(jwtTokenUtil.getTokenHead())) {
            String authToken = preToken.substring(jwtTokenUtil.getTokenHead().length());
            String username = jwtTokenUtil.parseToken(authToken).get("username").asString();
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetails userDetails = userService.loadUserByUsername(username);
                try{
                    jwtTokenUtil.validate(authToken);
                }catch (JWTVerificationException e) {
//                    ServletUtil.responseWriter(httpServletResponse,ResultGenerator.genFailResult(e.getMessage()));
                    httpServletResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,e.getMessage());
                }
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }else{
//            ServletUtil.responseWriter(httpServletResponse,ResultGenerator.genFailResult("token格式不符合规范"));
            httpServletResponse.sendError(HttpServletResponse.SC_BAD_REQUEST,"格式不符合规范");
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
