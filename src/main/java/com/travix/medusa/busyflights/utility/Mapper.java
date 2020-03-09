package com.travix.medusa.busyflights.utility;

import com.travix.medusa.busyflights.domain.*;

public class Mapper {

    public static Response mapToughToBusy(ToughJetResponse toughJetResponse){
        BusyFlightsResponse busyFlightsResponse =new BusyFlightsResponse();
        busyFlightsResponse.setAirline(toughJetResponse.getCarrier());
        busyFlightsResponse.setArrivalDate(toughJetResponse.getInboundDateTime().substring(11,19));
        busyFlightsResponse.setCabinclass("-");
        busyFlightsResponse.setDepartureAirportCode(toughJetResponse.getDepartureAirportName());
        busyFlightsResponse.setDestinationAirportCode(toughJetResponse.getArrivalAirportName());
        busyFlightsResponse.setDepartureDate(toughJetResponse.getOutboundDateTime().substring(11,19));
        busyFlightsResponse.setSupplier("ToughJet");
        busyFlightsResponse.setFare(toughJetResponse.getBasePrice()+ toughJetResponse.getTax()- toughJetResponse.getDiscount());

        return busyFlightsResponse;
    }

    public static Response mapCrazyToBusy(CrazyAirResponse crazyAirResponse){
        BusyFlightsResponse busyFlightsResponse =new BusyFlightsResponse();
        busyFlightsResponse.setAirline(crazyAirResponse.getAirline());
        busyFlightsResponse.setArrivalDate(crazyAirResponse.getArrivalDate().substring(11));
        busyFlightsResponse.setCabinclass(crazyAirResponse.getCabinclass());
        busyFlightsResponse.setDepartureAirportCode(crazyAirResponse.getDepartureAirportCode());
        busyFlightsResponse.setDestinationAirportCode(crazyAirResponse.getDestinationAirportCode());
        busyFlightsResponse.setDepartureDate(crazyAirResponse.getDepartureDate().substring(11));
        busyFlightsResponse.setSupplier("CrazyAir");
        busyFlightsResponse.setFare(crazyAirResponse.getPrice());

        return busyFlightsResponse;
    }

    public static Request mapParamReqObj(String origin, String dest, String departureDate, String returnDate, int passengerNum) {
        Request request=new Request();
        request.setOrigin(origin);
        request.setDestination(dest);
        request.setReturnDate(returnDate);
        request.setNumberOfPassengers(passengerNum);
        request.setDepartureDate(departureDate);
        return request;
    }

}
