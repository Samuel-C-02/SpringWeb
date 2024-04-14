package it.pc.test.WebSpringApp.exceptions;

public abstract class AbstractCheckedException extends Exception {

    private HttpErroreMessage error;

    public AbstractCheckedException(HttpErroreMessage error) {
        super(error.getErrorMessage());
        this.error = error;
    }

    public AbstractCheckedException(String message) {
        super(message);
        this.error = new HttpErroreMessage(message, message);
    }

    public HttpErroreMessage getError() {
        return error;
    }

    public void setError(HttpErroreMessage error) {
        this.error = error;
    }
}
