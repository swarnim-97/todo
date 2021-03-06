package dev.swarnim.project.errorhandler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;

/**
 * Returning the custom response from the Exception Handler
 * We have already defined our error format so we can start using it in the no-response-content ExceptionHandler
 * we implemented in the first place.
 * To do that, we just create a new error object and return it with a ResponseEntity wrapper.
 */

@RestControllerAdvice
public class RestControllerErrorAdvice {

    @Value("${error.api.version}")
    private String apiVersion;

    @Value("${error.sendreport.uri}")
    private String sendReportUri;

    @Value("todo.in")
    private String domain;

    @Value("Something unexpected happened")
    private String reason;

    @ExceptionHandler(TodoApplicationValidationFailedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorMaster> handleTodoApplicationValidationFailedException
            (TodoApplicationValidationFailedException exception) {
        final ErrorMaster error = new ErrorMaster(
                apiVersion,
                exception.getErrorCode(),
                exception.getErrorMessage(),
                domain,
                reason,
                exception.getDetailedErrorMessage(),
                sendReportUri, new Date());

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMaster> handleInvalidArgumentException(InvalidArgumentException exception) {
        final ErrorMaster error = new ErrorMaster(
                apiVersion,
                exception.getErrorCode(),
                exception.getMessage(),
                domain,
                exception.getDetailedErrorMessage(),
                exception.getMessage(),
                sendReportUri, new Date());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
