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

    public List<ToughJetResponse> findAll() {

        return repo.findAll();
    }

    public List<ToughJetResponse> findByParam(Request request) {
        List<ToughJetResponse> resList=new ArrayList<>();
        resList= repo.findByParam(request);
        for(ToughJetResponse entity:resList){

            entity.setBasePrice(request.getNumberOfPassengers()*entity.getBasePrice());
            entity.setDiscount(request.getNumberOfPassengers()*entity.getDiscount());
            entity.setTax(request.getNumberOfPassengers()*entity.getTax());
        }

        return resList;
    }

    public ToughJetResponse findById(int i) {
        return repo.findById(i);
    }

    @Override
    public Response save(Response response) {
        return null;
    }

    public ToughJetResponse saveFlight(ToughJetResponse response) {
        return repo.save(response);
    }

    public void delete(int i) {
        repo.delete(i);
    }

}
