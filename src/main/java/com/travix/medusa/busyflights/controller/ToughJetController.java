package com.travix.medusa.busyflights.controller;


import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.exception.InputIncorrectException;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.exception.NoDataException;
import com.travix.medusa.busyflights.service.ToughJetServ;
import com.travix.medusa.busyflights.utility.InputChecking;
import com.travix.medusa.busyflights.utility.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tough-jet-management")
public class ToughJetController {

    @Autowired
    ToughJetServ service;

    @GetMapping("/flights")
    public List<Response> getAllFlights() {

        List<Response> list=service.findAll();
        if(list.size()==0)
            throw new NoDataException("No Data Found");

        return list;
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
    public List<Response> getFlights(@RequestParam String from, @RequestParam String to
                                           , @RequestParam String inboundDate, @RequestParam String outboundDate
                                            , @RequestParam int numberOfAdults) throws Exception {

        Request request = Mapper.mapParamReqObj(from,to,outboundDate,inboundDate,numberOfAdults);

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
