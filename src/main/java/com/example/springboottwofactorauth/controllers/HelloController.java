package com.example.springboottwofactorauth.controllers;

import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@RestController
public class HelloController {

    //Testing with async
    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    @Async
    public String hello() {

        //As authentication object is stored in security context we can access it now
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return "Hello!" + authentication.getName();
    }

//    Security context is tightly linked on the thread that called it. So, By default if other
//    thread calls it will get null.
//    By default, security context strategy is THREAD_LOCAL(Localizes the values to only local thread)
//    InheritableThreadLocal - Copies the info from the parent thread to child thread.
}
