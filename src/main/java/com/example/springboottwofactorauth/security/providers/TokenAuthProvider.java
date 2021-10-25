package com.example.springboottwofactorauth.security.providers;

import com.example.springboottwofactorauth.security.authentication.TokenAuth;
import com.example.springboottwofactorauth.security.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * @Author Amimul Ehsan
 * @Created at 10/25/21
 * @Project Spring boot two factor auth
 */

@Configuration
public class TokenAuthProvider implements AuthenticationProvider {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException {
        String token = authentication.getName();
        boolean exists = tokenManager.contains(token);

        if (exists) {
            return new TokenAuth(token, null, null);
        } else {
            throw new BadCredentialsException("Error!");
        }
    }

    @Override
    public boolean supports( Class<?> authentication ) {
        return TokenAuth.class.equals(authentication);
    }
}
