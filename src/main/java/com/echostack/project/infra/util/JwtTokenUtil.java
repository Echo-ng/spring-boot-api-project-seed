package com.echostack.project.infra.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.echostack.project.model.entity.Role;
import com.echostack.project.model.entity.User;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: Echo
 * @Date: 2019/3/23 17:16
 * @Description:
 */
@Component
@Slf4j
@Data
public class JwtTokenUtil {
    private String secret; //秘钥

    @Value("${jwt.issuer}")
    private String issuer;

    @Value("${jwt.expired}")
    private Long expired;

    @Value("${jwt.header.key}")
    private String headerKey;

    @Value("${jwt.token.head}")
    private String tokenHead;

//    @Autowired
//    RedisTemplate redisTemplate;

    public JwtTokenUtil(){
//        this.secret = UUID.randomUUID().toString();
        this.secret = "travel";
    }


//    public SecretKey generalKey(){
//        String stringKey = this.secret;//本地配置文件中加密的密文7786df7fc3a34e26a61c034d5ec8245d
//        byte[] encodedKey = Base64Utils.decodeFromString(stringKey);//本地的密码解码[B@152f6e2
//        System.out.println(encodedKey);//
//        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, "AES");// 根据给定的字节数组使用AES加密算法构造一个密钥，使用 encodedKey中的始于且包含 0 到前 leng 个字节这是当然是所有。（后面的文章中马上回推出讲解Java加密和解密的一些算法）
//        return key;
//    }

    public Map<String, Claim> parseToken(String token) {
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getClaims();
    }

    public String createToken(User user) throws JWTCreationException{
        String token;
        //失效日期
        Date now = new Date();
        Date expiredDate = new Date(now.getTime() + expired);
        //设置秘钥
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        //获取角色清单
        List<String> roleList = user.getRoles().parallelStream()
                                                .map(Role::getName)
                                                .collect(Collectors.toList());
        String[] roles = new String[roleList != null ? roleList.size() : 0];
        roleList.toArray(roles);
        //生成token
        token =JWT.create()
                .withIssuer(this.issuer)
                .withClaim("userId",user.getId())
                .withClaim("username",user.getUsername())
                .withClaim("email",user.getEmail())
                .withClaim("mobile",user.getMobile())
                .withArrayClaim("roles",roles)
                .withIssuedAt(now)
                .withExpiresAt(expiredDate)
                .sign(algorithm);
//        redisTemplate.opsForSet().add(user.getUsername(),token);
        return token;
    }

    public void validate(String token) {

        //设置秘钥
        Algorithm algorithm = Algorithm.HMAC256(this.secret);
        DecodedJWT jwt = JWT.require(algorithm)
                            .acceptLeeway(1)
                            .build().verify(token);
        //从缓存判断是否存在
//            redisTemplate.hasKey()
    }

    public void del(String authToken) {
//        redisTemplate.delete(authToken);
    }
}
