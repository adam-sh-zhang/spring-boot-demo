package com.test.demo.service.impl;

import com.test.demo.exception.authentication.InvalidPasswordException;
import com.test.demo.exception.authentication.InvalidUsernameException;
import com.test.demo.model.TokenResult;
import com.test.demo.model.User;
import com.test.demo.service.TokenService;
import com.test.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Adam.Zhang
 * @date 2018/1/23
 */
@Service
public class TokenServiceImpl extends DefaultTokenServices implements TokenService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UserService userService;

    private TokenStore tokenStore;

    @Autowired
    public TokenServiceImpl(TokenStore tokenStore) {
        this.tokenStore = tokenStore;
        this.setTokenStore(tokenStore);
        this.setSupportRefreshToken(true);
    }

    @Override
    public TokenResult generateToken(String username, String password) {
        User user = userService.getUserByUsername(username);
        if (user == null) {
            throw new InvalidUsernameException("username incorrect");
        }

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidPasswordException("password incorrect");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();
        HashMap<String, String> requestParameters = new HashMap<>();
        String clientId = "default";
        OAuth2Request storedRequest = new OAuth2Request(requestParameters, clientId, authorities, true, null, null, null, null, null);

        UsernamePasswordAuthenticationToken userAuthentication = new UsernamePasswordAuthenticationToken(user, null, authorities);
        OAuth2Authentication auth2Authentication = new OAuth2Authentication(storedRequest, userAuthentication);
        auth2Authentication.setAuthenticated(true);
        OAuth2AccessToken accessToken = this.createAccessToken(auth2Authentication);

        TokenResult result = convertToTokenResult(accessToken);

        return result;
    }

    private TokenResult convertToTokenResult(OAuth2AccessToken accessToken) {
        TokenResult result = new TokenResult();
        result.setAccessToken(accessToken.getValue());
        result.setExpiresIn(accessToken.getExpiresIn());
        result.setRefreshToken(accessToken.getRefreshToken().getValue());
        result.setTokenType(accessToken.getTokenType());

        return result;
    }
}
