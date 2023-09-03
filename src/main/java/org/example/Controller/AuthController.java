package org.example.Controller;

import org.example.Model.SignInRequest;
import org.example.Model.SignInResponse;
import org.example.dto.SignInRequest;
import org.example.dto.SignInResponse;
import org.example.service.security.AuthService;
import org.example.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Controller
@RequestMapping("auth")
public class AuthController {
    public static final String AUTH_CONTROLLER_METHOD_SIGN_IN_PATH_ANT = "/auth/signin/public";
    public static final String AUTH_CONTROLLER_METHOD_CONFIG_PATH_ANT = "/auth/config/public";

    @Autowired
    private AuthService authService;

    @PostMapping("/signin/public")
    public SignInResponse signIn(SignInRequest signInRequest) {
        return authService.signIn(signInRequest);
    }

    @PostMapping("/signout")

    public void signOut() {
        authService.signOut();
    }

    @GetMapping("/keepalive")
    public void keepalive() {
        authService.keepalive();
    }

}
