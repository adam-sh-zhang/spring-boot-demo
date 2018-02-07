package com.test.demo.service;

import com.test.demo.model.TokenResult;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
public interface TokenService {
    TokenResult generateToken(String username, String password);
}
