package it.pc.test.WebSpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends AbstractUncheckedException {
    public BadRequestException(HttpErroreMessage error) {
        super(error);
    }

    public BadRequestException(String errorMessage) {
    super(errorMessage);
    }

    public BadRequestException(HttpErroreMessage e,  Throwable cause) {
        super(e, cause);
    }
}
