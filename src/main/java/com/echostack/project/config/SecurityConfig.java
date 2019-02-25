package com.echostack.project.config;

import com.echostack.project.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Author: Echo
 * @Date: 2019/2/22 15:18
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    protected UserDetailsService userDetailsService(){
        return new UserServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(userDetailsService())
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()//关闭csrf保护
                .authorizeRequests()//返回请求的安全级别的去安全细节
                .antMatchers(HttpMethod.GET, //静态资源允许无条件访问
                        "/",
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js")
                .permitAll()//无条件允许访问
                .antMatchers("/auth/**").permitAll()//认证请求允许我无条件访问
                .antMatchers("/admin/**").hasIpAddress("127.0.0.1")//特定ip允许访问
                .antMatchers("/admin/**").access("hasAuthority('ROLE_ADMIN')")//hasAuthority('ROLE_ADMIN')为真是允许访问，即拥有admin权限的可以访问，access支持SpEL
                .anyRequest().authenticated()//其他的情况都需要认证
                .and()
                .formLogin().loginPage("/login")//登录页面路径
                .failureUrl("/login?error")//登录失败的跳转路径
                .defaultSuccessUrl("/index")//登录成功的跳转页
                .and().logout().logoutSuccessUrl("/login?logout=true").permitAll();
    }
}
