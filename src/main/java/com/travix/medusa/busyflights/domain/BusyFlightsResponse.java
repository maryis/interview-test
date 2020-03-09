package com.travix.medusa.busyflights.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Setter
@Getter
@NoArgsConstructor

@Component
@Scope("prototype")
public class BusyFlightsResponse implements Response {

    private String airline;
    private String supplier;
    private double fare; // total price
    private String cabinclass; //E , B
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;//ISO_LOCAL_DATE  '2011-12-03'
    private String arrivalDate;//ISO_LOCAL_DATE

    @Override
    public void printResponse() {

    }


}
