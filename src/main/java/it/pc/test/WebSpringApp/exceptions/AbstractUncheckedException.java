package it.pc.test.WebSpringApp.exceptions;

import it.pc.test.WebSpringApp.utils.GlobalConstants;
import org.springframework.http.HttpStatus;

public abstract class AbstractUncheckedException extends RuntimeException {

    private HttpErroreMessage error;

    public AbstractUncheckedException(HttpErroreMessage error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public AbstractUncheckedException(String message) {
        super(message);
        this.error = new HttpErroreMessage(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public AbstractUncheckedException(HttpErroreMessage e, Throwable cause) {
        super(cause);
        this.error = e;
    }

    public AbstractUncheckedException() {
        this.error = getError();
    }

    public HttpErroreMessage getError() {
        return error != null
               ? error
               : new HttpErroreMessage(GlobalConstants.HTTP_DEFAULT_MESSAGE, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    public void setError(HttpErroreMessage error) {
        this.error = error;
    }
}
