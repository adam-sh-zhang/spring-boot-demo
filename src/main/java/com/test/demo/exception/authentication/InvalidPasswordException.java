package com.test.demo.exception.authentication;

import com.test.demo.constant.ErrorCodes;
import com.test.demo.exception.CustomizedException;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
public class InvalidPasswordException extends CustomizedException {

    public InvalidPasswordException(String msg) {
        super(msg);
    }

    @Override
    public int getErrorCode() {
        return ErrorCodes.INVALID_PASSWORD;
    }
}
