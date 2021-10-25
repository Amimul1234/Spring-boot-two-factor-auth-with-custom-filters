package com.example.springboottwofactorauth.config;

import com.example.springboottwofactorauth.security.filter.TokenAuthFilter;
import com.example.springboottwofactorauth.security.filter.UserNamePasswordAuthFilter;
import com.example.springboottwofactorauth.security.providers.OtpAuthProvider;
import com.example.springboottwofactorauth.security.providers.TokenAuthProvider;
import com.example.springboottwofactorauth.security.providers.UserNamePasswordAuthProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    private final OtpAuthProvider otpAuthProvider;

    @Autowired
    private TokenAuthProvider tokenAuthProvider;

    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Autowired
    private UserNamePasswordAuthProvider userNamePasswordAuthProvider;

    @Autowired
    private UserNamePasswordAuthFilter userNamePasswordAuthFilter;

    public ProjectConfig( OtpAuthProvider otpAuthProvider ) {
        this.otpAuthProvider = otpAuthProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) {
        auth.authenticationProvider(userNamePasswordAuthProvider)
                .authenticationProvider(otpAuthProvider)
                .authenticationProvider(tokenAuthProvider);
    }

    @Override
    protected void configure( HttpSecurity http ) {
        http
                .addFilterAt(userNamePasswordAuthFilter, BasicAuthenticationFilter.class)
                .addFilterAfter(tokenAuthFilter, BasicAuthenticationFilter.class);
    }

    //Change security context holder strategy
    @Bean
    public InitializingBean initializingBean() {
        return () -> SecurityContextHolder.setStrategyName(
                SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
        );
    }
}
