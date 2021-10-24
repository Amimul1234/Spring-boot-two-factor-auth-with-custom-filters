package com.example.springboottwofactorauth.security.authentication;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */


public class UserNamePasswordAuth extends UsernamePasswordAuthenticationToken {

    public UserNamePasswordAuth( Object principal, Object credentials ) {
        super(principal, credentials);
    }

    public UserNamePasswordAuth( Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities ) {
        super(principal, credentials, authorities);
    }
}
