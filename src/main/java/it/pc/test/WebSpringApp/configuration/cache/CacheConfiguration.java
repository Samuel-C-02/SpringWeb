package it.pc.test.WebSpringApp.configuration.cache;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class CacheConfiguration {

    @Bean
    public Cache<String, UserDetails> getUserCache() {
        return Caffeine.newBuilder()
                .expireAfterWrite(30, TimeUnit.MINUTES) // Time before cleaning the cache
                .maximumSize(20) // maximum number of users
                .build();
    }

}
