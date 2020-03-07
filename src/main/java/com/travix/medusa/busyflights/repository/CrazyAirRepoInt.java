package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.CrazyAirResponse;

import java.util.List;

public interface CrazyAirRepoInt {

    public List<CrazyAirResponse> findByParam(Request request);

    public List<CrazyAirResponse> findAll();

    public CrazyAirResponse findById(int i);

    public CrazyAirResponse save(CrazyAirResponse crazyAirResponse);

    public void delete(int i);
}
