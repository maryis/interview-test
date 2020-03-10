package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.Response;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.utility.*;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ToughJetRepo implements FlightRepo {

    static List<Response> list=new ArrayList<>();
    static {

        for(int i=0;i<10;i++)
            list.add(new ToughJetResponse("Tough",(i+1)*12.5,(i+1)*2.5,2,"DP1",
                    "DA1","2019-03-04T0"+i+":02:00Z","2019-03-05T2"+i+":02:00Z" ));
    }

    @Override
    public List<Response> findAll(){
        return list;
    }

    @Override
    public Response findById(int i){
        return list.get(i);
    }

    @Override
    public Response save(Response toughJetResponse){
        list.add(toughJetResponse);
        return toughJetResponse;
    }

    @Override
    public List<Response> findByParam(Request request) {

        List<Response> res=new ArrayList<>();

        LocalDate reqInTime=TimeUtiltiy.getDate(request.getReturnDate());
        LocalDate reqOutTime=TimeUtiltiy.getDate(request.getDepartureDate());

        for(Response entity:list){
            ToughJetResponse toughJetResponse= (ToughJetResponse) entity;
            LocalDate inTime=TimeUtiltiy.getDate(toughJetResponse.getInboundDateTime());
            LocalDate outTime=TimeUtiltiy.getDate(toughJetResponse.getOutboundDateTime());


            if(reqInTime.compareTo(inTime)==0 && reqOutTime.compareTo(outTime)==0)

                if(toughJetResponse.getDepartureAirportName().equals(request.getOrigin())&&
                        toughJetResponse.getArrivalAirportName().equals(request.getDestination()))
                    res.add(toughJetResponse);
        }

        return res;
    }

    @Override
    public void delete(int i){
        list.remove(i);
    }
}
