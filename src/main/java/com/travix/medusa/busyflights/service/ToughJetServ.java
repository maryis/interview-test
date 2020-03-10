package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.repository.ToughJetRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToughJetServ implements FlightService {

    @Autowired
    ToughJetRepo repo;//comment static reason

    public List<Response> findAll() {

        return repo.findAll();
    }

    public List<Response> findByParam(Request request) {
        List<Response> resList=new ArrayList<>();
        resList= repo.findByParam(request);
        for(Response entity:resList){

            ToughJetResponse toughJetResponse= (ToughJetResponse) entity;
            toughJetResponse.setBasePrice(request.getNumberOfPassengers()*toughJetResponse.getBasePrice());
            toughJetResponse.setDiscount(request.getNumberOfPassengers()*toughJetResponse.getDiscount());
            toughJetResponse.setTax(request.getNumberOfPassengers()*toughJetResponse.getTax());
        }

        return resList;
    }

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
