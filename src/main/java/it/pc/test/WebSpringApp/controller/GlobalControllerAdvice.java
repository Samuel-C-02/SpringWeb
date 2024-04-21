package it.pc.test.WebSpringApp.controller;

import it.pc.test.WebSpringApp.exceptions.BadRequestException;
import it.pc.test.WebSpringApp.exceptions.HttpErroreMessage;
import it.pc.test.WebSpringApp.exceptions.InternalErrorException;
import it.pc.test.WebSpringApp.utils.LogUtils;
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
        LogUtils.logException(exc);
        return new ResponseEntity<>(new HttpErroreMessage("UNEXPECTED ERROR", exc.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InternalErrorException.class)
    public ResponseEntity<HttpErroreMessage> internalServerErrorHandler(InternalErrorException exc) {
        LogUtils.logException(exc);
        return new ResponseEntity<>(exc.getError(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<HttpErroreMessage> badRequestErrorHandler(BadRequestException exc) {
        LogUtils.logException(exc);
        return new ResponseEntity<>(exc.getError(), HttpStatus.BAD_REQUEST);
    }


}
