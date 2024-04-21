package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.exceptions.InternalErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Error handling class that return a custom message in case of exceptions
 */
@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<HttpErroreMessage> unexpectedErrorHandler(Exception exc) {

        return new ResponseEntity<>(new HttpErroreMessage("UNEXPECTED ERROR", exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<HttpErroreMessage> internalServerErrorHandler(InternalErrorException exc) {

        return new ResponseEntity<>(exc.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpErroreMessage> badRequestErrorHandler(BadRequestException exc) {

        return new ResponseEntity<>(exc.getError(), HttpStatus.BAD_REQUEST);
    }


}
