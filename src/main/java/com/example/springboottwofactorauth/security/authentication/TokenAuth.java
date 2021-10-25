package com.example.springboottwofactorauth.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author Amimul Ehsan
 * @Created at 10/25/21
 * @Project Spring boot two factor auth
 */


public class TokenAuth extends UsernamePasswordAuthenticationToken {

    public TokenAuth( Object principal, Object credentials ) {
        super(principal, credentials);
    }

    public TokenAuth( Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities ) {
        super(principal, credentials, authorities);
    }
}
