package com.echostack.project.config;

import com.echostack.project.component.*;
import com.echostack.project.infra.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @Author: Echo
 * @Date: 2019/2/22 15:18
 * @Description:
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userServiceImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    AuthSuccessHandler authenticationSuccessHandler;


    @Autowired
    AuthFailureHandler authenticationFailureHandler;


    @Autowired
    JwtAuthenticationFilter jwtAuthenticationFilter;

    @Autowired
    JWTLoginFilter jwtLoginFilter;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

//    @Bean
//    public JWTLoginFilter jwtLoginFilter() throws Exception {
//        JWTLoginFilter jwtLoginFilter = new JWTLoginFilter(authenticationManager());
//        jwtLoginFilter.setJwtTokenUtil(jwtTokenUtil);
//        return jwtLoginFilter;
//    }



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                // 设置UserDetailsService
                .userDetailsService(userDetailsService)
                // 使用BCrypt进行密码的hash
                .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
//                http.httpBasic()
                .loginPage("/login")
                .loginProcessingUrl("/signin")
                .successHandler(authenticationSuccessHandler)
                .failureHandler(authenticationFailureHandler)
                .and()
//                .rememberMe()
//                .tokenRepository(persistentTokenRepository())
//                .tokenValiditySeconds(3600)
//                .and()
                .authorizeRequests()
                .antMatchers("signin"
                        , "/login")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .logout()
                .and().csrf().disable()
                .addFilter(jwtLoginFilter)
                .addFilterAfter(jwtAuthenticationFilter,JWTLoginFilter.class);

//                .apply(smsAuthenticationConfig);
    }
}
