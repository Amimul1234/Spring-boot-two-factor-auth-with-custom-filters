package com.example.springboottwofactorauth.service;

import com.example.springboottwofactorauth.entities.User;
import com.example.springboottwofactorauth.repos.UserRepo;
import com.example.springboottwofactorauth.security.model.SecurityUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@Component
public class JpaUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public JpaUserDetailsService( UserRepo userRepo ) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
        var o = userRepo.findByUserName(username);
        User u = o.orElseThrow(() -> new UsernameNotFoundException("Error!"));
        return new SecurityUser(u);
    }
}
