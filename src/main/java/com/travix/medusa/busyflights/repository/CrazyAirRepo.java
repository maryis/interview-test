package com.travix.medusa.busyflights.repository;

import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.utility.TimeUtiltiy;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CrazyAirRepo implements CrazyAirRepoInt{

    static List<CrazyAirResponse> list=new ArrayList<>();
    static {

        for(int i=0;i<10;i++)
            list.add(new CrazyAirResponse("Crazy",(i+1)*12.5,"E","DP1","DA1",
                    "2019-03-04T0"+i+":02:00","2019-03-05T2"+i+":02:00" ));
    }
    @Override
    public List<CrazyAirResponse> findByParam(Request request) {

        List<CrazyAirResponse> res = new ArrayList<>();

        LocalDate reqInTime = TimeUtiltiy.getDate(request.getReturnDate());
        LocalDate reqOutTime = TimeUtiltiy.getDate(request.getDepartureDate());

        for (CrazyAirResponse entity : list) {
            LocalDate inTime = TimeUtiltiy.getDate(entity.getArrivalDate());
            LocalDate outTime = TimeUtiltiy.getDate(entity.getDepartureDate());


            if (reqInTime.compareTo(inTime) == 0 && reqOutTime.compareTo(outTime) == 0)

                if (entity.getDepartureAirportCode().equals(request.getOrigin()) &&
                        entity.getDestinationAirportCode().equals(request.getDestination()))
                    res.add(entity);
        }

        return res;
    }

        @Override
    public List<CrazyAirResponse> findAll(){
        return list;
    }

    @Override
    public CrazyAirResponse findById(int i){
        return list.get(i);
    }


    @Override
    public CrazyAirResponse save(CrazyAirResponse crazyAirResponse){
        list.add(crazyAirResponse);
        return crazyAirResponse;
    }

    @Override
    public void delete(int i){
        list.remove(i);
    }
}
