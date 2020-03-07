package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.utility.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToughJetRepo implements ToughJetRepoInt {

    static List<ToughJetResponse> list=new ArrayList<>();
    static {

        for(int i=0;i<10;i++)
            list.add(new ToughJetResponse("Tough",(i+1)*12.5,(i+1)*2.5,2,"DP1",
                    "DA1","2019-03-04T0"+i+":02:00Z","2019-03-05T2"+i+":02:00Z" ));
    }

    @Override
    public List<ToughJetResponse> findAll(){
        return list;
    }

    @Override
    public ToughJetResponse findById(int i){
        return list.get(i);
    }

    @Override
    public ToughJetResponse save(ToughJetResponse toughJetResponse){
        list.add(toughJetResponse);
        return toughJetResponse;
    }

    @Override
    public List<ToughJetResponse> findByParam(Request request) {

        List<ToughJetResponse> res=new ArrayList<>();

        LocalDate reqInTime=TimeUtiltiy.getDate(request.getReturnDate());
        LocalDate reqOutTime=TimeUtiltiy.getDate(request.getDepartureDate());

        for(ToughJetResponse entity:list){
            LocalDate inTime=TimeUtiltiy.getDate(entity.getInboundDateTime());
            LocalDate outTime=TimeUtiltiy.getDate(entity.getOutboundDateTime());


            if(reqInTime.compareTo(inTime)==0 && reqOutTime.compareTo(outTime)==0)

                if(entity.getDepartureAirportName().equals(request.getOrigin())&&
                        entity.getArrivalAirportName().equals(request.getDestination()))
                    res.add(entity);
        }

        return res;
    }

    @Override
    public void delete(int i){
        list.remove(i);
    }
}
