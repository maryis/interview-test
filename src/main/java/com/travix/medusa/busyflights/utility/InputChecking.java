package com.travix.medusa.busyflights.utility;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.exception.InputIncorrectException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class InputChecking {

    public static boolean isRequestValid(Request request){
        try {
            DateTimeFormatter.ISO_LOCAL_DATE.parse(request.getReturnDate());
            DateTimeFormatter.ISO_LOCAL_DATE.parse(request.getDepartureDate());

        } catch (DateTimeParseException ex) {
            throw new InputIncorrectException("Date Format Incorrect");
        }
        LocalDate inTime= TimeUtiltiy.getDate(request.getReturnDate());
        LocalDate outTime= TimeUtiltiy.getDate(request.getDepartureDate());
        if(inTime.compareTo(outTime) <=0){

            throw new InputIncorrectException("Date Order Incorrect");
        }

        if(request.getOrigin().length()!=3||request.getDestination().length()!=3){

            throw new InputIncorrectException("To-From Lengths Incorrect");
        }

        return true;
    }
}
