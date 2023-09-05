package org.example.services;


import lombok.Getter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    @Getter
    private PasswordEncoder passwordEncoder;
}
