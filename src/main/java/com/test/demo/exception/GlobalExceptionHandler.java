package com.test.demo.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.test.demo.constant.ErrorCodes;
import com.test.demo.model.ResponseEntity;
import com.test.demo.util.LogUtil;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.ClassUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author Adam.Zhang
 * @date 2017/11/21
 */
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomizedException.class)
    public ResponseEntity handleCustomException(HttpServletRequest request, CustomizedException ex) {
        ResponseEntity responseEntity = new ResponseEntity(null);
        responseEntity.setErrorCode(ex.getErrorCode());
        responseEntity.setErrorMsg(ex.getMessage());
        return responseEntity;
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(HttpServletRequest request, Exception ex) {
        ResponseEntity responseEntity = new ResponseEntity(null);
        responseEntity.setErrorCode(-1);
        responseEntity.setErrorMsg("Uncaught exception");
        LogUtil.error(GlobalExceptionHandler.class, "Uncaught exception occurred", ex);
        return responseEntity;
    }

}
