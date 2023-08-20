package com.example.admin.user;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PasswordEncoderTests {
    @Test
    public void testPasswordEncoder() {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String password = "123456";
        String encoderPassword = passwordEncoder.encode(password);

        System.out.println(encoderPassword);

        boolean matches = passwordEncoder.matches(password, encoderPassword);
        assertThat(matches).isTrue();

    }
}
