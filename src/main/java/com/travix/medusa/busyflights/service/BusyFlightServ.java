package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class BusyFlightServ {


    @Autowired
    RestTemplate restTemplate;

    @Value("${app.toughjet.api.complete.url}")
    private String toughUrl;

    @Value("${app.crazyair.api.complete.url}")
    private String crayUrl;

    @Value("${app.api.password}")
    private String password;

    @Value("${app.api.username}")
    private String username;

    public List<BusyFlightsResponse> findByParam(Request request) {

        List<BusyFlightsResponse> busyFlightsEntities=new ArrayList<>();
        List<ToughJetResponse> toughJetResponses= findToughByParam(request);
        List<CrazyAirResponse> crazyAirResponses= findCrazyByParam(request);

        for(ToughJetResponse entity: toughJetResponses){
            busyFlightsEntities.add(Mapper.mapToughToBusy(entity));
        }
        for(CrazyAirResponse entity1: crazyAirResponses){
            busyFlightsEntities.add(Mapper.mapCrazyToBusy(entity1));
        }
        //sort

        busyFlightsEntities.sort(new Comparator<BusyFlightsResponse>() {
            @Override
            public int compare(BusyFlightsResponse o1, BusyFlightsResponse o2) {
                return (int) (o1.getFare()-o2.getFare());
            }
        });

        return busyFlightsEntities;
    }


   public List<ToughJetResponse> findToughByParam(Request request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.setBasicAuth(username, password);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(toughUrl.toString())
                .queryParam("outboundDate", request.getDepartureDate())
                .queryParam("inboundDate", request.getReturnDate())
                .queryParam("from", request.getOrigin())
                .queryParam("to", request.getDestination())
                .queryParam("numberOfAdults", request.getNumberOfPassengers());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<ToughJetResponse>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<ToughJetResponse>>(){});

        return responseEntity.getBody();
    }

   public List<CrazyAirResponse> findCrazyByParam(Request request) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.setBasicAuth(username, password);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(crayUrl)
                .queryParam("departureDate", request.getDepartureDate())
                .queryParam("returnDate", request.getReturnDate())
                .queryParam("origin", request.getOrigin())
                .queryParam("destination", request.getDestination())
                .queryParam("passengerCount", request.getNumberOfPassengers());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        ResponseEntity<List<CrazyAirResponse>> responseEntity = restTemplate.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<CrazyAirResponse>>(){});

        return responseEntity.getBody();
    }

}
