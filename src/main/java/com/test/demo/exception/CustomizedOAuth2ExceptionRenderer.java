package com.test.demo.exception;

import com.test.demo.constant.ErrorCodes;
import com.test.demo.model.ResponseEntity;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.DefaultOAuth2ExceptionRenderer;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.HashMap;

/**
 * @author Adam.Zhang
 * @date 2017/12/12
 */
public class CustomizedOAuth2ExceptionRenderer extends DefaultOAuth2ExceptionRenderer {

    private HashMap<String, Integer> oauth2ExceptionErrorCodeMapping;

    public CustomizedOAuth2ExceptionRenderer() {
        oauth2ExceptionErrorCodeMapping = new HashMap<>();
        oauth2ExceptionErrorCodeMapping.put("unauthorized", ErrorCodes.UNAUTHORIZED);
        oauth2ExceptionErrorCodeMapping.put("invalid_token", ErrorCodes.INVALID_TOKEN);
    }

    @Override
    public void handleHttpEntityResponse(HttpEntity<?> responseEntity, ServletWebRequest webRequest) throws Exception {
        OAuth2Exception oAuth2Exception = (OAuth2Exception) responseEntity.getBody();
        System.out.println(oAuth2Exception);
        ResponseEntity customizedResponseEntity = convertOauth2Exception(oAuth2Exception);
        org.springframework.http.ResponseEntity response = new org.springframework.http.ResponseEntity(customizedResponseEntity, HttpStatus.OK);
        super.handleHttpEntityResponse(response, webRequest);
    }


    private ResponseEntity convertOauth2Exception(OAuth2Exception oauth2Exception) {
        String oauth2ErrorCode = oauth2Exception.getOAuth2ErrorCode();
        ResponseEntity customizedResponseEntity = new ResponseEntity();
        if (oauth2ExceptionErrorCodeMapping.containsKey(oauth2ErrorCode)) {
            customizedResponseEntity.setErrorCode(oauth2ExceptionErrorCodeMapping.get(oauth2ErrorCode));
            customizedResponseEntity.setErrorMsg(oauth2Exception.getMessage());
        } else {
            customizedResponseEntity.setErrorCode(-1);
            customizedResponseEntity.setErrorMsg("Uncaught exception");
        }

        return customizedResponseEntity;
    }
}
