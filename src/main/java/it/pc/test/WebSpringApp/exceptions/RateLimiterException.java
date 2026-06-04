package it.pc.test.WebSpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.TOO_MANY_REQUESTS)
public class RateLimiterException extends AbstractUncheckedException {

    public RateLimiterException(HttpErroreMessage error) {
        super(error);
    }

    public RateLimiterException(String errorMessage) {
        super(errorMessage);
    }

    public RateLimiterException(HttpErroreMessage e, Throwable cause) {
        super(e, cause);
    }
}
