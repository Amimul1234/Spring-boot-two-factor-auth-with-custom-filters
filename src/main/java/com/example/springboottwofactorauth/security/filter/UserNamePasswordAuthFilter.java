package com.example.springboottwofactorauth.security.filter;

import com.example.springboottwofactorauth.entities.Otp;
import com.example.springboottwofactorauth.repos.OtpRepo;
import com.example.springboottwofactorauth.security.authentication.OtpAuth;
import com.example.springboottwofactorauth.security.authentication.UserNamePasswordAuth;
import com.example.springboottwofactorauth.security.manager.TokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
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

@Configuration
public class UserNamePasswordAuthFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private OtpRepo otpRepo;

    @Autowired
    private TokenManager tokenManager;

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
            Authentication a = new UserNamePasswordAuth(userName, password);
            Authentication authenticate = authenticationManager.authenticate(a);

            //We generate an otp
            Otp otpEntity = new Otp();

            otpEntity.setUserName(userName);
            otpEntity.setOtp(String.valueOf(new Random().nextInt(9999) + 1000));
            otpRepo.save(otpEntity);
        } else {
            //OtpAuth is a class that extends authentication contract
            Authentication a = new OtpAuth(userName, otp);
            a = authenticationManager.authenticate(a);

            //Issue a token to user
            var tok = UUID.randomUUID().toString();
            tokenManager.add(tok);
            response.setHeader("Authorization", tok);
        }
    }

    @Override
    protected boolean shouldNotFilter( HttpServletRequest request ) throws ServletException {
        return !request.getServletPath().equals("/login");
    }
}
