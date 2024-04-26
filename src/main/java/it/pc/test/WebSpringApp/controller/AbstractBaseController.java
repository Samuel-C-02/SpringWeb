package it.pc.test.WebSpringApp.controller;

import org.springframework.web.bind.annotation.GetMapping;

public abstract class AbstractBaseController {

    @GetMapping("/test")
    public String check() {
        return "OK";
    }

}
