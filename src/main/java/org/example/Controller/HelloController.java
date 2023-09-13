package org.example.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("hello")
public class HelloController {
    public static final String HELLO_CONTROLLER_METHOD_PUBLIC_PATH_ANT = "/hello/public";

    @GetMapping("/public")
    public String helloPublic() {
        System.out.println("public");
        return "hello public";
    }

    @GetMapping("/authenticated")
    public String helloAuthenticated() {
        System.out.println("auth");

        return "hello authenticated";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String helloAdmin() {
        System.out.println("admin");

        return "hello admin";
    }

}
