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
public class CrazyAirResponse implements Response{

    private String airline;
    private double price;//for one passenger
    private String cabinclass; //E ,B
    private String departureAirportCode;
    private String destinationAirportCode;
    private String departureDate;//ISO_LOCAL_DATE_TIME  '2011-12-03T10:15:30'
    private String arrivalDate;//ISO_LOCAL_DATE_TIME

    public CrazyAirResponse(String airline, double price, String cabinclass, String departureAirportCode, String destinationAirportCode, String departureDate, String arrivalDate) {
        this.airline = airline;
        this.price = price;
        this.cabinclass = cabinclass;
        this.departureAirportCode = departureAirportCode;
        this.destinationAirportCode = destinationAirportCode;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public void printResponse() {

    }

}
