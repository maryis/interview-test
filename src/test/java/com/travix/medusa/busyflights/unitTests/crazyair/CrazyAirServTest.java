package com.travix.medusa.busyflights.unitTests.crazyair;

import com.travix.medusa.busyflights.domain.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.repository.CrazyAirRepo;
import com.travix.medusa.busyflights.repository.ToughJetRepo;
import com.travix.medusa.busyflights.service.CrazyAirServ;
import com.travix.medusa.busyflights.service.ToughJetServ;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;

@RunWith(SpringRunner.class)
@SpringBootTest

public class CrazyAirServTest {

    @Autowired
    CrazyAirServ crazyAirServ;

    @MockBean
    CrazyAirRepo crazyAirRepo;

    @Autowired
    Request request;

    List<CrazyAirResponse> list;
    int num;

    @Before
    public void initialize(){

        num=4;

        request.setNumberOfPassengers(num);
        request.setDepartureDate("2019-03-04");
        request.setReturnDate("2019-03-05");
        request.setOrigin("DP1");
        request.setDestination("DA1");

        list=new ArrayList<>();
        CrazyAirResponse entity=new CrazyAirResponse();
        entity.setPrice(33);
        entity.setAirline("Crazy");
        entity.setDepartureAirportCode("DFG");
        entity.setDestinationAirportCode("GGG");
        list.add(entity);

    }

    @Test
    public void testFindParam(){

        Mockito.when(crazyAirRepo.findByParam(any())).thenReturn(list);
        List<CrazyAirResponse> result= crazyAirServ.findByParam(request);

        Assert.assertEquals(1,result.size());
        Assert.assertEquals(list.get(0).getPrice(), result.get(0).getPrice(),0);



    }
}