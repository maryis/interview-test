package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.domain.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

public interface FlightService {

    public List<? extends Response> findAll();

    public List<? extends Response> findByParam(Request request) ;

    public Response findById(int i);

    public Response save(Response response);

    public void delete(int i);

}
