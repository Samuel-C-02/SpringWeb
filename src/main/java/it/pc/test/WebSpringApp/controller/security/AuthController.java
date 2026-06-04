package it.pc.test.WebSpringApp.controller.security;

import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import it.pc.test.WebSpringApp.dto.security.TokenDTO;
import it.pc.test.WebSpringApp.dto.security.UserDTO;
import it.pc.test.WebSpringApp.entity.security.UserEntity;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.exceptions.RateLimiterException;
import it.pc.test.WebSpringApp.service.security.UserService;
import it.pc.test.WebSpringApp.utils.LogUtils;
import it.pc.test.WebSpringApp.utils.annotation.AdminUser;
import it.pc.test.WebSpringApp.utils.annotation.BaseUser;
import it.pc.test.WebSpringApp.utils.annotation.NoSwaggerLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/current-user-info")
    public UserDTO getCurrentUser() {
        UserEntity currentUser = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userService.getUserById(currentUser.getId());
    }

    @NoSwaggerLogin
    @PostMapping("/signup")
    public UserDTO addUser(@RequestBody UserDTO user) {
        if (user == null) {
            return null;
        }

        return userService.saveUser(user);
    }

    @RateLimiter(name = "http-calls-rate-limiter", fallbackMethod = "rateLimiterErrorHandler")
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

    /**
     * Method that gets called when the rate limit is exceeded.
     * Throw and exception handled by the GlobalControllerAdvice.class
     */
    public TokenDTO rateLimiterErrorHandler(RequestNotPermitted ex) {
        LogUtils.log.warn("Too many http request received");
        throw new RateLimiterException(new HttpErroreMessage("Too many http request received, wait 5 sec.", ex.getMessage()), ex);
    }
}
