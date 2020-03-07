package com.travix.medusa.busyflights.exception;


// Convert a predefined exception to an HTTP Status code :optional
//@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Not Found" ,code = HttpStatus.NOT_FOUND)

public class InputIncorrectException extends RuntimeException {

    public InputIncorrectException(String message) {
        super(message);
    }
}
