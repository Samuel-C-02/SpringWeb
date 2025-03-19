package it.pc.test.WebSpringApp.configuration.security;

import com.github.benmanes.caffeine.cache.Cache;
import it.pc.test.WebSpringApp.service.security.JWTService;
import it.pc.test.WebSpringApp.utils.GlobalConstants;
import it.pc.test.WebSpringApp.utils.LogUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalTime;

@Component
public class JWTAuthFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private Cache<String, UserDetails> userAuthenticatedCache;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader(GlobalConstants.AUTHORIZATION_HEADER);

        if (authHeader == null || !authHeader.startsWith(GlobalConstants.TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            return;
        }

        final String tokenJWT = authHeader.substring(7);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            final String username = jwtService.getJwtUsername(tokenJWT);
            if (username != null) {

                // Use the cache to avoid a db call for every request
                UserDetails userDB = userAuthenticatedCache.getIfPresent(username);
                if (userDB == null) {
                    userDB = userDetailsService.loadUserByUsername(username);
                    userAuthenticatedCache.put(username, userDB);
                    LogUtils.log.info("User saved in the cache for the next 30 minutes. Timestamp: {}", LocalTime.now());
                }

                // Check if token is valid and the username is correct
                if (jwtService.isTokenValid(tokenJWT, userDB) && username.equals(userDB.getUsername())) {
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDB, null, userDB.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        filterChain.doFilter(request, response);

    }
}
