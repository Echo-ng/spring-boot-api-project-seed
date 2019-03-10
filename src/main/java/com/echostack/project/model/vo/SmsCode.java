package com.echostack.project.model.vo;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author: Echo
 * @Date: 2019/2/28 15:45
 * @Description:
 */
@Data
public class SmsCode {
    private String code;
    private LocalDateTime expireTime;

    public SmsCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpire() {
        return LocalDateTime.now().isAfter(expireTime);
    }
}
