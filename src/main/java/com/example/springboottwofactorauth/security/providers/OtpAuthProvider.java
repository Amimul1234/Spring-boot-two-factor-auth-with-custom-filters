package com.example.springboottwofactorauth.security.providers;

import com.example.springboottwofactorauth.entities.Otp;
import com.example.springboottwofactorauth.repos.OtpRepo;
import com.example.springboottwofactorauth.security.authentication.OtpAuth;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

        Optional<Otp> otpOptional = otpRepo.findOtpByUserName(userName);

        Otp otp1 = otpOptional.orElseThrow(() -> new BadCredentialsException("Error!"));

        return new OtpAuth(userName, otp1, List.of(() -> "read"));
    }

    @Override
    public boolean supports( Class<?> authentication ) {
        return OtpAuth.class.equals(authentication);
    }
}
