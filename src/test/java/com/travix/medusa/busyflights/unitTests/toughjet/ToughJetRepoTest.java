package com.travix.medusa.busyflights.unitTests.toughjet;

import com.travix.medusa.busyflights.domain.Request;
import com.travix.medusa.busyflights.domain.ToughJetResponse;
import com.travix.medusa.busyflights.repository.ToughJetRepo;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest

public class ToughJetRepoTest {

    @Autowired
    ToughJetRepo repo;

    @Autowired
    Request request;

    @Before
    public void initialize(){


        request.setNumberOfPassengers(3);
        request.setDepartureDate("2019-03-04");
        request.setReturnDate("2019-03-05");
        request.setOrigin("DP1");
        request.setDestination("DA1");
    }

    @Test
    public void testfindByParam() {

        List<ToughJetResponse> list=new ArrayList<>();
        list=repo.findByParam(request);
        Assert.assertEquals(10,list.size());
//        list.stream().forEach(i-> System.out.println(i.getBasePrice()));
    }

    @Order(1)
    @Test
    public void testSave(){
        ToughJetResponse response=new ToughJetResponse();
        response.setBasePrice(34);
        response.setArrivalAirportName("FGH");

        repo.save(response);
        List<ToughJetResponse> list=repo.findAll();

        Assert.assertEquals(list.size(),11);

    }
    @Order(2)
    @Test
    public void testDelete(){

        repo.delete(1);
        List<ToughJetResponse> list=repo.findAll();

        Assert.assertEquals(10,list.size());

    }
}
