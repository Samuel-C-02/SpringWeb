package it.pc.test.WebSpringApp.controller.security;

import it.pc.test.WebSpringApp.dto.security.TokenDTO;
import it.pc.test.WebSpringApp.dto.security.UserDTO;
import it.pc.test.WebSpringApp.service.security.UserService;
import it.pc.test.WebSpringApp.utils.annotation.AdminUser;
import it.pc.test.WebSpringApp.utils.annotation.BaseUser;
import it.pc.test.WebSpringApp.utils.annotation.NoSwaggerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/user-info/{id}")
    public UserDTO getUserInfo(@PathVariable(name = "id") Integer id) {
        return userService.getUserById(id);
    }

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

    @BaseUser
    @PutMapping("/update-user")
    public UserDTO updateUser(@RequestBody UserDTO updatedUser) {
        return userService.updateUser(updatedUser);
    }

    @AdminUser
    @DeleteMapping("/delete-user")
    public boolean deleteUser(@RequestParam Integer userId) {
        return userService.deleteUser(userId);
    }

    // TODO Endpoint to refresh jwt

}
