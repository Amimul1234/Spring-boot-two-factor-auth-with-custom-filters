package com.example.springboottwofactorauth.security.filter;

import com.example.springboottwofactorauth.security.authentication.TokenAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author Amimul Ehsan
 * @Created at 10/25/21
 * @Project Spring boot two factor auth
 */

@Configuration
public class TokenAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response,
                                     FilterChain filterChain ) throws ServletException, IOException {

        var token = request.getHeader("Authorization");

        TokenAuth tokenAuth = new TokenAuth(token, null);
        Authentication authentication = authenticationManager.authenticate(tokenAuth);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter( HttpServletRequest request ) throws ServletException {
        return request.getServletPath().equals("/login");
    }
}
