package org.example.Controller;

import org.example.Model.PersonModel;
import org.example.Model.RegisterModel;
import org.example.Model.SignInRequest;
import org.example.Model.SignInResponse;
import org.example.services.Security.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void register (@RequestBody RegisterModel registerModel) throws AccountException {
        System.out.println("reg cnrt");
        authService.register(registerModel);
    }

    @PostMapping("/signin/public")
    public SignInResponse signIn(@RequestBody SignInRequest signInRequest) throws AccountException {
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
