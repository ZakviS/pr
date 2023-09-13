package org.example.Controller;

import org.example.Model.*;
import org.example.services.Security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.security.auth.login.AccountException;


@Controller
@RequestMapping("auth")
public class AuthController {
    public static final String AUTH_CONTROLLER_METHOD_SIGN_IN_PATH_ANT = "/auth/signin/public";
    public static final String AUTH_CONTROLLER_METHOD_CONFIG_PATH_ANT = "/auth/config/public";
    public static final String AUTH_CONTROLLER_METHOD_REG_PATH_ANT = "/auth/register";


    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<SignInResponse> register (@RequestBody RegisterModel registerModel) throws AccountException {
        System.out.println("reg cnrt");

        return new ResponseEntity<>(authService.register(registerModel), HttpStatus.OK);

    }


    @PostMapping("/signin/public")
    public ResponseEntity<SignInResponse> signIn(@RequestBody SignInRequest signInRequest) throws AccountException {
//        return authService.signIn(signInRequest);
        return new ResponseEntity<>(authService.signIn(signInRequest), HttpStatus.OK);

    }

    @PostMapping("/signout")
    public ResponseEntity<?> signOut() {
        authService.signOut();
        return new ResponseEntity<>(HttpStatus.OK);
    }



    @GetMapping("/keepalive")
    public ResponseEntity<?> keepalive() {
        authService.keepalive();
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
