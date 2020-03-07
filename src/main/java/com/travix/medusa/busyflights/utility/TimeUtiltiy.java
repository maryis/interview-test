package com.travix.medusa.busyflights.utility;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class TimeUtiltiy {

    public static LocalDateTime getTime(String time, String formatter){
        switch (formatter){
            case "ISO_INSTANT":
                Instant inTimeIns= Instant.from(DateTimeFormatter.ISO_INSTANT.parse(time));
                return LocalDateTime.ofInstant(inTimeIns, ZoneOffset.UTC);

            case "ISO_LOCAL_DATE_TIME":
                return  LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            case "ISO_LOCAL_TIME":
                return  LocalDateTime.parse(time, DateTimeFormatter.ISO_LOCAL_TIME);

            case "ISO_LOCAL_DATE":
                return  LocalDateTime.parse(time+"T00:00:00", DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        }
        return null;
    }
    public static LocalDate getDate(String time){
        return LocalDate.parse(time.substring(0,10),DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
