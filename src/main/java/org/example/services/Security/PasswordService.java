package org.example.services.Security;


import jakarta.annotation.PostConstruct;
import lombok.Getter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.regex.Pattern;

@Service
public class PasswordService {

    @Getter
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    private void init() {
        SecureRandom secureRandom = new SecureRandom();
        passwordEncoder = new BCryptPasswordEncoder(10, secureRandom);
    }
}
