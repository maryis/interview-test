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
@RequiredArgsConstructor


@Component
@Scope("prototype")public class ToughJetResponse {


    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;//ISO_INSTANT  '2011-12-03T10:15:30Z'
    private String inboundDateTime;//ISO_INSTANT  '2011-12-03T10:15:30Z'

}
