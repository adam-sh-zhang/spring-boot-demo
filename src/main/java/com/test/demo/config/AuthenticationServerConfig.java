package com.test.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;

/**
 * @author Adam.Zhang
 * @date 2017/11/21
 */
@Configuration
public class AuthenticationServerConfig {

    @Bean
    public TokenStore tokenStore() {
        return new InMemoryTokenStore();
    }

}
