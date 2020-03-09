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
public class ToughJetResponse implements Response {


    private String carrier;
    private double basePrice;
    private double tax;
    private double discount;
    private String departureAirportName;
    private String arrivalAirportName;
    private String outboundDateTime;//ISO_INSTANT  '2011-12-03T10:15:30Z'
    private String inboundDateTime;//ISO_INSTANT  '2011-12-03T10:15:30Z'

    public ToughJetResponse(String carrier, double basePrice, double tax, double discount, String departureAirportName, String arrivalAirportName, String outboundDateTime, String inboundDateTime) {
        this.carrier = carrier;
        this.basePrice = basePrice;
        this.tax = tax;
        this.discount = discount;
        this.departureAirportName = departureAirportName;
        this.arrivalAirportName = arrivalAirportName;
        this.outboundDateTime = outboundDateTime;
        this.inboundDateTime = inboundDateTime;
    }

    @Override
    public void printResponse() {

    }
}
