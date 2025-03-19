package it.pc.test.WebSpringApp.controller.security;

import it.pc.test.WebSpringApp.utils.annotation.AdminUser;
import it.pc.test.WebSpringApp.utils.annotation.BaseUser;
import it.pc.test.WebSpringApp.utils.annotation.NoSwaggerLogin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestController {

    @NoSwaggerLogin
    @GetMapping("/no-auth")
    public boolean testNoLogin() {
        return true;
    }

    @GetMapping("/auth")
    public boolean testLogin() {
        return true;
    }

    @AdminUser
    @GetMapping("/auth/admin")
    public boolean testAdmin() {
        return true;
    }

    @BaseUser
    @GetMapping("auth/user")
    public boolean testUser() {
        return true;
    }

}
