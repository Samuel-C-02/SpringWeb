package it.pc.test.WebSpringApp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalErrorException extends AbstractUncheckedException {
    public InternalErrorException(HttpErroreMessage error) {
        super(error);
    }

    public InternalErrorException(String message) {
        super(message);
    }

    public InternalErrorException() {
        setError(new HttpErroreMessage(
                getError().getErrorMessage(),
                getError().getExceptionMessage()));
    }
}
