package com.springsecurity.springsecurityProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


@Component
public class MyFilter implements Filter, AuthenticationProvider
{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
    {

    }

    @Override
    public void destroy() {

    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
       return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
