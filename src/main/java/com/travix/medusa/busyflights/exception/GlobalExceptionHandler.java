package com.travix.medusa.busyflights.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({ InputIncorrectException.class })
    public ResponseEntity<Object>  handleIncorrectInputException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);

    }
    @ExceptionHandler({ NoDataException.class })
    public ResponseEntity<Object>  handleNoDataException(
            Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(
                ex.getMessage(), new HttpHeaders(), HttpStatus.NO_CONTENT);

    }

}
