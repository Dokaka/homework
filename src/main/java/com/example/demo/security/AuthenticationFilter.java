package com.example.demo.security;

import com.example.demo.response.ErrorResponse;
import org.springframework.http.HttpStatus;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class AuthenticationFilter implements Filter {
    private final String API_KEY = "mytest_key_for_authentication";

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        String authenticationHeader = req.getHeader("Authentication");

        if (null == authenticationHeader || false == authenticationHeader.equals(API_KEY)) {
            res.sendError(HttpStatus.UNAUTHORIZED.value(), ErrorResponse.AUTHENTICATION_FAILED.getErrorMessage());
            return;
        }
        chain.doFilter(request, response);
    }
}
