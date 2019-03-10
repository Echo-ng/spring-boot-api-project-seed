package com.echostack.project.infra.common;

import com.echostack.project.model.vo.SmsCode;
import org.apache.commons.lang3.RandomStringUtils;

/**
 * @Author: Echo
 * @Date: 2019/2/28 15:47
 * @Description:
 */
public class SMSUtil {

    public static SmsCode createSMSCode(int length,int expireIn) {
        String code = RandomStringUtils.randomNumeric(length);
        return new SmsCode(code, expireIn);
    }
}
