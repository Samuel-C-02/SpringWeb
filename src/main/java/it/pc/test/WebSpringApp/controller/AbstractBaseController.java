package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.utils.annotation.NoSwaggerLogin;
import org.springframework.web.bind.annotation.GetMapping;

public abstract class AbstractBaseController {

    @NoSwaggerLogin
    @GetMapping("/test")
    public String check() {
        return "OK";
    }

}
