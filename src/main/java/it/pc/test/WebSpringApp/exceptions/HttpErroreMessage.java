package it.pc.test.WebSpringApp.exceptions;

import it.pc.test.WebSpringApp.utils.GlobalConstants;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Class that will be used in the @ControllerAdvice to send custom error messages in to the Client
 */
@Data
public class HttpErroreMessage {

    private String errorMessage;
    private String exceptionMessage;
    private LocalDateTime errorDate;
    private HttpStatus httpStatus;

    public HttpErroreMessage() {
        this.errorMessage = GlobalConstants.HTTP_DEFAULT_MESSAGE;
        this.exceptionMessage = GlobalConstants.HTTP_DEFAULT_MESSAGE;
        this.errorDate = LocalDateTime.now();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpErroreMessage(String errorMessage, String exceptionMessage) {
        this.errorMessage = errorMessage;
        this.exceptionMessage = exceptionMessage;
        this.errorDate = LocalDateTime.now();
        this.httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    }

    public HttpErroreMessage(String errorMessage, String exceptionMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.exceptionMessage = exceptionMessage;
        this.errorDate = LocalDateTime.now();
        this.httpStatus = httpStatus;
    }

    public HttpErroreMessage(String errorMessage, HttpStatus httpStatus) {
        this.errorMessage = errorMessage;
        this.exceptionMessage = GlobalConstants.HTTP_DEFAULT_MESSAGE;
        this.errorDate = LocalDateTime.now();
        this.httpStatus = httpStatus;
    }
}
