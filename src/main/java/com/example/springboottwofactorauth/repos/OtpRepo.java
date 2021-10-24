package com.example.springboottwofactorauth.repos;

import com.example.springboottwofactorauth.entities.Otp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @Author Amimul Ehsan
 * @Created at 10/24/21
 * @Project spring-security-c1
 */

@Repository
public interface OtpRepo extends JpaRepository<Otp, Integer> {
    Optional<Otp> findOtpByUserName( String userName );
}
