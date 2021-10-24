package com.example.springboottwofactorauth.security.filter;

import com.example.springboottwofactorauth.entities.Otp;
import com.example.springboottwofactorauth.repos.OtpRepo;
import com.example.springboottwofactorauth.security.authentication.OtpAuth;
import com.example.springboottwofactorauth.security.authentication.UserNamePasswordAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.UUID;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@Component
public class UserNamePasswordAuthFilter extends OncePerRequestFilter {

    private AuthenticationManager authenticationManager;
    private OtpRepo otpRepo;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response,
                                     FilterChain filterChain ) {
//        Step 1 : UserName and password
//        Step 2: userName and otp

        var userName = request.getHeader("username");
        var password = request.getHeader("password");
        var otp = request.getHeader("otp");

        if (otp == null) {

            //step 1
            var a = new UserNamePasswordAuth(userName, password);
            Authentication authenticate = authenticationManager.authenticate(a);


            //We generate an otp
            Otp otpEntity = new Otp();

            otpEntity.setUserName(userName);
            otpEntity.setOtp(String.valueOf(new Random().nextInt(9999) + 1000));
            otpRepo.save(otpEntity);
        } else {
            var a = new OtpAuth(userName, otp);
            Authentication authenticate = authenticationManager.authenticate(a);
            response.setHeader("Authorization", UUID.randomUUID().toString());
        }
    }

    @Override
    protected boolean shouldNotFilter( HttpServletRequest request ) throws ServletException {
        return !request.getServletPath().equals("/login");
    }

    @Autowired
    public void setAuthenticationManager( AuthenticationManager authenticationManager ) {
        this.authenticationManager = authenticationManager;
    }

    @Autowired
    public void setOtpRepo( OtpRepo otpRepo ) {
        this.otpRepo = otpRepo;
    }
}
