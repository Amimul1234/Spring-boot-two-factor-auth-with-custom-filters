package com.example.springboottwofactorauth.security.manager;

import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author Amimul Ehsan
 * @Created at 10/25/21
 * @Project Spring boot two factor auth
 */

@Component
public class TokenManager {

    private final Set<String> tokens = new HashSet<>();

    public void add( String token ) {
        tokens.add(token);
    }

    public boolean contains( String token ) {
        return tokens.contains(token);
    }
}
