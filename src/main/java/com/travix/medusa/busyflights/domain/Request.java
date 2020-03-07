package com.travix.medusa.busyflights.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Setter @Getter @NoArgsConstructor

@Component
@Scope("prototype")
public class Request {

    private String origin;
    private String destination;
    private String departureDate;//ISO_LOCAL_DATE
    private String returnDate;//ISO_LOCAL_DATE
    private int numberOfPassengers;
}