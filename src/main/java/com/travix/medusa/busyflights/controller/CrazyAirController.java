package com.travix.medusa.busyflights.controller;

import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.exception.InputIncorrectException;
import com.travix.medusa.busyflights.exception.NoDataException;
import com.travix.medusa.busyflights.service.CrazyAirServ;
import com.travix.medusa.busyflights.utility.InputChecking;
import com.travix.medusa.busyflights.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
    public List<Response> getAllFlights(){

        return service.findAll();
    }


    @GetMapping("/flights/{id}")
    public Response getFlightById(@PathVariable int id) {

        return service.findById(id);
    }

    @DeleteMapping("/flights/{id}")
    public Response deleteFlightById(@PathVariable int id) {

        service.delete(id);
        return null;
    }

    @PostMapping("/flights/")
    public Response saveFlight(@RequestBody ToughJetResponse entity) {

        service.save(entity);
        return entity;
    }

    @GetMapping("/flight")
    public List<Response> getFlights(@RequestParam String origin, @RequestParam String destination
            , @RequestParam String departureDate, @RequestParam String returnDate
            , @RequestParam int passengerCount) throws Exception {

        Request request = Mapper.mapParamReqObj(origin,destination,departureDate,returnDate,passengerCount);

        try {
            if(InputChecking.isRequestValid(request)) {
                List<Response> list=service.findByParam(request);

                return list;
            }
        } catch (InputIncorrectException e) {
            throw  e;//to be handled by global handler
        }

        return null;
    }

}
