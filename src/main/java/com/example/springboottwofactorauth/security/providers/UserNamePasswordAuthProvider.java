package com.example.springboottwofactorauth.security.providers;

import com.example.springboottwofactorauth.security.authentication.UserNamePasswordAuth;
import com.example.springboottwofactorauth.service.JpaUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@Component
public class UserNamePasswordAuthProvider implements AuthenticationProvider {

    private JpaUserDetailsService jpaUserDetailsService;
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException {

        var userName = authentication.getName();
        var password = (String) authentication.getCredentials();

        UserDetails userDetails = jpaUserDetailsService.loadUserByUsername(userName);

        if (passwordEncoder.matches(password, userDetails.getPassword())) {
            return new UserNamePasswordAuth(userName, password, userDetails.getAuthorities());
        }

        throw new BadCredentialsException("Error!");
    }

    @Override
    public boolean supports( Class<?> authentication ) {
        return UserNamePasswordAuth.class.equals(authentication);
    }

    @Autowired
    public void setJpaUserDetailsService( JpaUserDetailsService jpaUserDetailsService ) {
        this.jpaUserDetailsService = jpaUserDetailsService;
    }

    @Autowired
    public void setPasswordEncoder( PasswordEncoder passwordEncoder ) {
        this.passwordEncoder = passwordEncoder;
    }
}
