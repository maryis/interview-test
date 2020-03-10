package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.BusyFlightsResponse;
import com.travix.medusa.busyflights.exception.InputIncorrectException;
import com.travix.medusa.busyflights.exception.NoDataException;
import com.travix.medusa.busyflights.service.BusyFlightServ;
import com.travix.medusa.busyflights.utility.InputChecking;
import com.travix.medusa.busyflights.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

@RestController
@RequestMapping("/busy-flights-management")
public class BusyFlightsController {


    @Autowired
    BusyFlightServ service;

    @GetMapping(path = "/flight")
    public List<BusyFlightsResponse> getFlights(@RequestParam String origin, @RequestParam String destination
            , @RequestParam String departureDate, @RequestParam String returnDate
            , @RequestParam int numberOfPassengers) throws Exception {

        Request request =Mapper.mapParamReqObj(origin, destination, departureDate, returnDate, numberOfPassengers);

        try {
            if(InputChecking.isRequestValid(request)) {
                List<BusyFlightsResponse> result=service.findByParam(request);
                if(result.size()==0)
                    throw new NoDataException("No data found");
                return result;
            }
        } catch (InputIncorrectException e) {
            throw  e;//to be handled by global handler
        }

        return null;
    }

}
