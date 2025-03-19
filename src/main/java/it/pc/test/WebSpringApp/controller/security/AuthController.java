package it.pc.test.WebSpringApp.controller.security;

import it.pc.test.WebSpringApp.dto.security.TokenDTO;
import it.pc.test.WebSpringApp.dto.security.UserDTO;
import it.pc.test.WebSpringApp.service.security.UserService;
import it.pc.test.WebSpringApp.utils.annotation.NoSwaggerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @NoSwaggerLogin
    @PostMapping("/signup")
    public UserDTO addUser(@RequestBody UserDTO user) {
        if (user == null) {
            return null;
        }

        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public TokenDTO login(@RequestBody UserDTO loginUser) {
        return userService.login(loginUser);
    }

    // TODO Endpoint to get user info

    // TODO Endpoint to refresh jwt
}
