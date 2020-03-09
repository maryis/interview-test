package com.travix.medusa.busyflights.exception;


// Convert a predefined exception to an HTTP Status code :optional
//@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Not Found" ,code = HttpStatus.NOT_FOUND)

public class NoDataException extends RuntimeException {

    public NoDataException(String message) {
        super(message);
    }
}
