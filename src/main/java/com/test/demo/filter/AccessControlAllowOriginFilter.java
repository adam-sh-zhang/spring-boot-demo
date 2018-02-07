package com.test.demo.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Adam.Zhang on 2018/2/2.
 */
@Component
public class AccessControlAllowOriginFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (response instanceof HttpServletResponse) {
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Origin", "*");
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            ((HttpServletResponse) response).addHeader("Access-Control-Allow-Methods", "GET, POST, PUT,DELETE");
        }
        chain.doFilter(request, response);
    }
}
