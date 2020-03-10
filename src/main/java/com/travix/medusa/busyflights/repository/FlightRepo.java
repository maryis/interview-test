package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Response;

import java.util.List;

public interface FlightRepo {

    public List<Response> findByParam(Request request);

    public List<Response> findAll();

    public Response findById(int i);

    public Response save(Response crazyAirResponse);

    public void delete(int i);
}
