package it.pc.test.WebSpringApp.exceptions;


public class EntityNotFoundException extends AbstractUncheckedException {
    public EntityNotFoundException(HttpErroreMessage error) {
        super(error);
    }

    public EntityNotFoundException(String message) {
        super(message);
    }
}
