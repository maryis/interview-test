package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;

import java.util.List;

public interface ToughJetRepoInt {

    public List<ToughJetResponse> findAll();

    public ToughJetResponse findById(int i);

    public ToughJetResponse save(ToughJetResponse toughJetResponse);

    public List<ToughJetResponse> findByParam(Request request);

    public void delete(int i);
}
