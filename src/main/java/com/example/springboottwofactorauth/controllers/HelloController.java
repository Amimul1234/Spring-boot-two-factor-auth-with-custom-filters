package com.example.springboottwofactorauth.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@RestController
public class HelloController {

    @GetMapping(value = "hello", produces = MediaType.APPLICATION_JSON_VALUE)
    public String hello() {
        return "Hello!";
    }

}
