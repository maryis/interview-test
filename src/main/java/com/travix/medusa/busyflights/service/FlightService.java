package com.travix.medusa.busyflights.service;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;

import java.util.ArrayList;
import java.util.List;

public interface FlightService<T> {

    public List<T> findAll();

    public List<T> findByParam(Request request) ;

    public T findById(int i);

    public T save(T response);

    public void delete(int i);

}
