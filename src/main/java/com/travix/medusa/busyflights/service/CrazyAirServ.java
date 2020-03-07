package com.travix.medusa.busyflights.service;


import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.repository.CrazyAirRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CrazyAirServ {

    @Autowired
    CrazyAirRepo repo;//comment static reason

    public List<CrazyAirResponse> findAll() {
        return repo.findAll();
    }

    public List<CrazyAirResponse> findByParam(Request request) {
        List<CrazyAirResponse> resList = new ArrayList<>();
        resList = repo.findByParam(request);
        for (CrazyAirResponse entity : resList) {
            entity.setPrice(entity.getPrice() * request.getNumberOfPassengers());
        }
        return resList;

    }

    public CrazyAirResponse findById(int i) {
        return repo.findById(i);
    }

    public CrazyAirResponse save(CrazyAirResponse crazyAirResponse) {
        return repo.save(crazyAirResponse);
    }

    public void delete(int i) {
        repo.delete(i);
    }

}
