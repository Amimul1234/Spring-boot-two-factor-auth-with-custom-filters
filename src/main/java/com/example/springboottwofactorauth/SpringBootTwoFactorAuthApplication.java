package com.example.springboottwofactorauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class SpringBootTwoFactorAuthApplication {

    public static void main( String[] args ) {
        SpringApplication.run(SpringBootTwoFactorAuthApplication.class, args);
    }

}
