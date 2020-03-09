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
    public List<CrazyAirResponse> findAll() {
        return repo.findAll();
    }

    @Override
    public List<CrazyAirResponse> findByParam(Request request) {
        List<CrazyAirResponse> resList = new ArrayList<>();
        resList = repo.findByParam(request);
        for (CrazyAirResponse entity : resList) {
            entity.setPrice(entity.getPrice() * request.getNumberOfPassengers());
        }
        return resList;

    }

    @Override
    public CrazyAirResponse findById(int i) {
        return repo.findById(i);
    }

    @Override
    public Response saveFlight(Response response) {
        return null;
    }

    public CrazyAirResponse saveFlight(CrazyAirResponse response) {
        return repo.save(response);
    }

    public void delete(int i) {
        repo.delete(i);
    }

}
