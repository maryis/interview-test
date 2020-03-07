package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.exception.InputIncorrectException;
import com.travix.medusa.busyflights.service.CrazyAirServ;
import com.travix.medusa.busyflights.utility.InputChecking;
import com.travix.medusa.busyflights.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/crazy-air-management")
public class CrazyAirController {

    @Autowired
    CrazyAirServ service;

    @GetMapping("/flights")
    public List<CrazyAirResponse> getAllFlights(){

        return service.findAll();
    }
    @GetMapping("/time/{time}")
    public Instant getTime(@PathVariable String time){

        DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_TIME;
        return Instant.from(f.parse(time));
    }
    @GetMapping("/flight")
    public List<CrazyAirResponse> getFlights(@RequestParam String origin, @RequestParam String destination
            , @RequestParam String departureDate, @RequestParam String returnDate
            , @RequestParam int passengerCount) throws Exception {

        Request request = Mapper.mapParamReqObj(origin,destination,departureDate,returnDate,passengerCount);

        try {
            if(InputChecking.isRequestValid(request)) {
                return service.findByParam(request);
            }
        } catch (InputIncorrectException e) {
            throw  e;//to be handled by global handler
        }

        return null;
    }

}