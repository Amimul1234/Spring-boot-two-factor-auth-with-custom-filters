package com.example.springboottwofactorauth.security.providers;

import com.example.springboottwofactorauth.repos.OtpRepo;
import com.example.springboottwofactorauth.security.authentication.OtpAuth;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@Component
public class OtpAuthProvider implements AuthenticationProvider {

    private final OtpRepo otpRepo;

    public OtpAuthProvider( OtpRepo otpRepo ) {
        this.otpRepo = otpRepo;
    }

    @Override
    public Authentication authenticate( Authentication authentication ) throws AuthenticationException {

        String userName = authentication.getName();
        String otp = (String) authentication.getCredentials();

        var o = otpRepo.findOtpByUserName(userName);

        if (o.isPresent()) {
            return new OtpAuth(userName, otp, List.of(() -> "read"));
        }

        throw new BadCredentialsException("Error!");
    }

    @Override
    public boolean supports( Class<?> authentication ) {
        return OtpAuth.class.equals(authentication);
    }
}
