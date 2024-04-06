package it.pc.test.WebSpringApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "it.pc.test.WebSpringApp")
public class WebSpringAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSpringAppApplication.class, args);
    }

}
