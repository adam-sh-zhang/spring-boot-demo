package com.test.demo.model;

/**
 * Created by Adam.Zhang on 2017/11/21.
 */
public class ResponseEntity<T> {

    private Integer errorCode;
    private String errorMsg;
    private T result;

    public ResponseEntity() {

    }

    public ResponseEntity(T result) {
        this.setErrorCode(0);
        this.setResult(result);
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
