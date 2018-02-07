package com.test.demo.controller;

import com.test.demo.model.ResponseEntity;
import com.test.demo.model.TokenResult;
import com.test.demo.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
@RestController
public class AuthenticationController {

    @Autowired
    TokenService tokenService;

    @RequestMapping("/token")
    public ResponseEntity<TokenResult> getToken(
            @RequestParam(value = "username", required = false, defaultValue = "") String username,
            @RequestParam(value = "password", required = false, defaultValue = "") String password) {
        TokenResult tokenResult = tokenService.generateToken(username, password);
        return new ResponseEntity<>(tokenResult);
    }
}
