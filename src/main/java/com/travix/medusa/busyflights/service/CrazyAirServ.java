package com.travix.medusa.busyflights.service;


import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.repository.CrazyAirRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrazyAirServ implements FlightService {

    @Autowired
    CrazyAirRepo repo;//comment static reason

    @Override
    public List<Response> findAll() {
        return repo.findAll();
    }

    @Override
    public List<Response> findByParam(Request request) {
        List<Response> resList = new ArrayList<>();
        resList = repo.findByParam(request);
        for (Response entity : resList) {
            CrazyAirResponse crazyAirResponse= (CrazyAirResponse) entity;
            crazyAirResponse.setPrice(crazyAirResponse.getPrice() * request.getNumberOfPassengers());
        }
        return resList;

    }

    @Override
    public Response findById(int i) {
        return repo.findById(i);
    }

    @Override
    public Response save(Response response) {
        return null;
    }

    public Response saveFlight(Response response) {
        return repo.save(response);
    }

    public void delete(int i) {
        repo.delete(i);
    }

}
