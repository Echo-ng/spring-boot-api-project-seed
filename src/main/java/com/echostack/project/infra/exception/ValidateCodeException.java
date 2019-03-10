package com.echostack.project.infra.exception;


import org.springframework.security.core.AuthenticationException;

/**
 * @Author: Echo
 * @Date: 2019/2/28 14:32
 * @Description:
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 2214804803325728366L;

    public ValidateCodeException(String message) {
        super(message);
    }
}
