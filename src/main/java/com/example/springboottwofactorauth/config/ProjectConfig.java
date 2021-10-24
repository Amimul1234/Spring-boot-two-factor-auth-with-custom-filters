package com.example.springboottwofactorauth.config;

import com.example.springboottwofactorauth.security.filter.UserNamePasswordAuthFilter;
import com.example.springboottwofactorauth.security.providers.OtpAuthProvider;
import com.example.springboottwofactorauth.security.providers.UserNamePasswordAuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
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

    public ProjectConfig( OtpAuthProvider otpAuthProvider, UserNamePasswordAuthFilter userNamePasswordAuthFilter ) {
        this.otpAuthProvider = otpAuthProvider;
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure( AuthenticationManagerBuilder auth ) {

        var userPassProvider = new UserNamePasswordAuthProvider();

        auth.authenticationProvider(userPassProvider)
                .authenticationProvider(otpAuthProvider);
    }

    @Override
    protected void configure( HttpSecurity http ) {
        var filter = new UserNamePasswordAuthFilter();

        http
                .addFilterAt(filter, BasicAuthenticationFilter.class);
    }
}
