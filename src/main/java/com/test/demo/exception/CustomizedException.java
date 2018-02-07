package com.test.demo.exception;

/**
 * Created by Adam.Zhang on 2017/11/21.
 */
public abstract class CustomizedException extends RuntimeException {

    public CustomizedException() {
        super();
    }

    public CustomizedException(String msg) {
        super(msg);
    }

    public abstract int getErrorCode();
}
