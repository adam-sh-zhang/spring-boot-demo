package com.test.demo.config;

import com.test.demo.exception.CustomizedOAuth2ExceptionRenderer;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AuthenticationEntryPoint;

/**
 * @author Adam.Zhang
 * @date 2017/11/21
 */
@Configuration
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    OAuth2AuthenticationEntryPoint oAuth2AuthenticationEntryPoint = new OAuth2AuthenticationEntryPoint();

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/token", "/user/create").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.NEVER);
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        oAuth2AuthenticationEntryPoint.setExceptionRenderer(new CustomizedOAuth2ExceptionRenderer());
        resources.authenticationEntryPoint(oAuth2AuthenticationEntryPoint);
    }
}
